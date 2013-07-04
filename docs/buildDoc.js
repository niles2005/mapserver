//var $ = require('jquery');
//var jsdom = require('jsdom');
var api = require('./api.json');
var __ = require('underscore');
var stringUtil = require('underscore.string');

var fs = require('fs');
var doc1 = fs.readFileSync('./part1.html', "utf-8");
var doc2 = fs.readFileSync('./part2.html', "utf-8");
var doc3 = fs.readFileSync('./part3.html', "utf-8");
// var part1 = doc1.split('\r\n');
// var part2 = doc2.slice('\r\n');
// var part3 = doc3.slice('\r\n');
var menuPart = [];
var contentPart = [];
for (var i = 0; i < api.length; i++) {
	var module = api[i]["module"];
	var title = api[i]["title"];
	var desc = api[i]["desc"]
	var items = api[i]["items"];

	
	menuPart.push('<a class="toc_title" href="#'+module+'">' +module+ '</a>');
	menuPart.push("<ul class=\"toc_section\">");
	__.each(items, function(item) {
		menuPart.push('<li>- <a href="#list">' + item.name + '</a></li>');
	});
	menuPart.push('</ul>');

	
	contentPart.push('<h2 id="'+module+'"> '+title+'</h2>');
	contentPart.push('<p>'+desc+'</p>');
	__.each(items, function(item) {
		contentPart.push('<p id="' + item.name + '">');
		contentPart.push('<b class="header">' + item.name + '</b><code>' + item.code + '</code>');
		contentPart.push('<br><span class="alias">Return: <b>' + item.rettype + '</b></span>');
		contentPart.push('<pre>' + item.info + '</pre>');
		// contentPart.push(item.info);
		contentPart.push('</p><br>');
	});

};
var menu = menuPart.join("\r\n");
var content = contentPart.join("\r\n");

// var total = part1.concat(menuPart).concat(part2).concat(contentPart).concat(part3);
// var newDoc = total.join("\r\n");

// console.log(newDoc);

// fileWriteStream.write(newDoc);

fs.writeFileSync('./newDoc1.html', doc1, 'utf8');
fs.appendFileSync('./newDoc1.html', menu, 'utf8');
fs.appendFileSync('./newDoc1.html', doc2, 'utf8');
fs.appendFileSync('./newDoc1.html', content, 'utf8');
fs.appendFileSync('./newDoc1.html', doc3, 'utf8');
//newDoc.pipe(fileWriteStream);