var fs = require('fs'),
	path = require('path'),
	strUtil = require('underscore.string');

var gitConfig = {
	'niles2005': {
		'srcPath': 'D:\\mywork\\mapserver\\workspace\\mapserver\\build\\classes',
		'destPath': 'E:\\apache-tomcat-7.0.29\\webapps\\mapflow\\WEB-INF\\classes'
	},
	'rayzang8': {
		'srcPath': 'D:\\mywork\\mapserver\\workspace\\mapserver\\build\\classes',
		'destPath': 'E:\\apache-tomcat-7.0.29\\webapps\\mapflow\\WEB-INF\\classes'
	}
}

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
			for (var k in arr) {
				//SSH  git@github.com:gitUser/test.git
				//HTTPS  https://github.com/gitUser/test.git
				//Subversion  https://github.com/gitUser/test
				if (strUtil.contains(arr[k], 'github.com')) {
					arr[k] = 'https://github.com/niles2005/mapserver.git';
					var pos0 = arr[k].indexOf('github.com');
					var pos1 = arr[k].indexOf('/', pos0 + 11);
					if (pos0 > 0 && pos1 > pos0) {
						var gitUser = arr[k].substring(pos0 + 11, pos1);
						var thePath = gitConfig[gitUser];
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

					}
				}
			}
			// console.log(data);
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