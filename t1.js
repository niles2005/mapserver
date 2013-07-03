var fs = require('fs'),
	strUtil = require('underscore.string');

var gitConfigFile = '.git/config';
fs.exists(gitConfigFile, function(exists) {
	if (exists) {
		fs.readFile(gitConfigFile, {
			encoding: 'utf-8'
		}, function(err, data) {
			if (err) {
				throw err;
			}
			var arr = data.split('\n');
			for(var k in arr) {
				if(strUtil.contains(arr[k],'@github.com:')) {
				console.log('xxx:' + arr[k]);
				}
			}
			// console.log(data);
		});
	} else {

	}
});