var api = require('./api.json');
var __ = require('underscore');
var stringUtil = require('underscore.string');
var targetFile = './newDoc.html';
var charCode = 'utf-8';

var fs = require('fs');
var doc1 = fs.readFileSync('./part1.html', charCode);
var doc2 = fs.readFileSync('./part2.html', charCode);
var doc3 = fs.readFileSync('./part3.html', charCode);

var menuPart = [];
var contentPart = [];
makeDocBody();

function makeDocBody() {
	for (var i = 0; i < api.length; i++) {
		var module = api[i]["module"];
		var title = api[i]["title"];
		var desc = api[i]["desc"]
		var items = api[i]["items"];

		menuPart.push('<a class="toc_title" href="#' + module + '">' + module + '</a>');
		menuPart.push("<ul class=\"toc_section\">");
		contentPart.push('<h2 id="' + module + '"> ' + title + '</h2>');
		contentPart.push('<p>' + desc + '</p>');
		__.each(items, function(item) {
			menuPart.push('<li>- <a href="#list">' + item.name + '</a></li>');
			contentPart.push('<p id="' + item.name + '">');
			contentPart.push('<b class="header">' + item.name + '</b><code>' + item.code + '</code>');
			contentPart.push('<br><span class="alias">Return: <b>' + item.rettype + '</b></span>');
			contentPart.push('<pre>' + item.info + '</pre>');
			contentPart.push('</p><br>');
		});
		menuPart.push('</ul>');
	}

}

var menu = menuPart.join("\r\n");
var content = contentPart.join("\r\n");

makeFile([doc1, menu, doc2, content, doc3]);

function makeFile(contentArr) {
	for (var i = 0; i < contentArr.length; i++) {
		if (i == 0) {
			fs.writeFileSync(targetFile, contentArr[i], charCode);
		} else {
			fs.appendFileSync(targetFile, contentArr[i], charCode);
		}
	}
}