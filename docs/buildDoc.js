//var $ = require('jquery');
//var jsdom = require('jsdom');
var api = require('./api.json');
var __ = require('underscore');
var stringUtil = require('underscore.string');

var fs = require('fs');
var doc1 = fs.readFileSync('./part1.html',"utf-8");
var doc2 = fs.readFileSync('./part2.html',"utf-8");
var doc3 = fs.readFileSync('./part3.html',"utf-8");
var part1 = doc1.split('\r\n');
var part2 = doc2.slice('\r\n');
var part3 = doc3.slice('\r\n');


var items = api[0]["items"];
var titlePart = [];
titlePart.push("<ul class=\"toc_section\">");
__.each(items,function(item) {
    titlePart.push('<li>- <a href="#list">' + item.name + '</a></li>');
});
titlePart.push('</ul>');

var contentPart = [];
__.each(items,function(item){
    contentPart.push('<p id="'+item.name +'">');
    contentPart.push('<b class="header">'+item.name +'</b><code>'+item.code+'</code>');
    contentPart.push('<br><span class="alias">Return: <b>'+item.JSON+'</b></span>');
    contentPart.push('<pre>'+item.retcontent+'</pre>');
    contentPart.push(item.info);
    contentPart.push('</p><br>');
});


var total = part1.concat(titlePart).concat(part2).concat(contentPart).concat(part3);
var newDoc = total.join("\r\n");

// console.log(newDoc);

// var fileWriteStream = fs.createWriteStream('./newDoc.html');
// fileWriteStream.write(newDoc);

fs.writeFileSync('./newDoc1.html', doc1, 'utf8');
fs.appendFileSync('./newDoc1.html',doc2,'utf8');
fs.appendFileSync('./newDoc1.html',doc3,'utf8');
//newDoc.pipe(fileWriteStream);
