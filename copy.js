#!/usr/bin/env node

var fs = require('fs'),
	path = require('path'),
	strUtil = require('underscore.string');

var gitConfig = {
	'niles2005': {
		'srcPath': 'D:\\mywork\\mapserver\\workspace\\mapserver\\build\\classes',
		'destPath': 'E:\\apache-tomcat-7.0.29\\webapps\\mapflow\\WEB-INF\\classes'
	},
	'rayzang8': {
		'srcPath': 'D:\\workspace\\mapserver\\build\\classes',
		'destPath': 'D:\\apache-tomcat-7.0.40\\webapps\\mapflow\\WEB-INF\\classes'
	}
}


var gitConfigFile = '.git/gituser.txt';

fs.exists(gitConfigFile, function(exists) {
	if (exists) {
		fs.readFile(gitConfigFile, {
			encoding: 'utf-8'
		}, function(err, data) {
			if (err) {
				throw err;
			}
			var thePath = gitConfig[data];
			// console.log(thePath)
			if(thePath) {
				var destPath = thePath.destPath;
				var srcPath = thePath.srcPath;
				removeDir(destPath);
				copyDir(srcPath, destPath);
				console.log("copy successed!");
			} else {
				console.log("failed,can not find git user!");
			}

		});
	}
});


function removeDir(dirPath) {
	try {
		if (!fs.existsSync(dirPath)) {
			return;
		}
		var files = fs.readdirSync(dirPath);
	} catch (err) {
		console.error(err);
		return;
	}
	if (files.length > 0) {
		for (var i = 0; i < files.length; i++) {
			var filePath = dirPath + '/' + files[i];
			if (fs.statSync(filePath).isFile()) {
				fs.unlinkSync(filePath);
			} else {
				removeDir(filePath);
			}
		}

	}
	fs.rmdirSync(dirPath);
}

function copyDir(srcPath, destPath) {
	try {
		var lstat = fs.lstatSync(srcPath);
		fs.exists(destPath, function(exists) {
			if (exists) {

			} else {
				fs.mkdir(destPath, function() {
					var files = fs.readdirSync(srcPath);

					if (files.length > 0) {
						for (var i = 0; i < files.length; i++) {
							var filePath = srcPath + '/' + files[i];
							var newDestPath = destPath + '/' + files[i];
							if (fs.statSync(filePath).isFile()) {
								copyFile(filePath, newDestPath);
							} else {
								copyDir(filePath, newDestPath);
							}
						}
					}
				});
			}
		})
	} catch (err) {
		console.error(err);
		return;
	}
}

function copyFile(file, destPath) {
	// console.log(file)
	if (fs.statSync(file).isFile()) {
		var is = fs.createReadStream(file);
		var os = fs.createWriteStream(destPath);
		is.pipe(os);
	}
}