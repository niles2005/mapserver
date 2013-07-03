var fs = require('fs'),
	path = require('path');
	
var srcPath = 'D:\\workspace\\mapserver\\build\\classes';
var destPath = 'D:\\apache-tomcat-7.0.40\\webapps\\mapflow\\WEB-INF\\classes';

removeDir(destPath);

copyDir(srcPath, destPath);

function removeDir(dirPath) {
	try {
		if(!fs.existsSync(dirPath)) {
			return;
		}
		var files = fs.readdirSync(dirPath);
	} catch(err) {
		console.error(err);
		return;
	} 
	if(files.length > 0) {
		for(var i = 0;i<files.length;i++) {
			var filePath = dirPath + '/' + files[i];
			if(fs.statSync(filePath).isFile()) {
				fs.unlinkSync(filePath);	
			} else {
				removeDir(filePath);
			}
		}	
		
	}
	fs.rmdirSync(dirPath);	
}

function copyDir(srcPath,destPath) {
	try {
		var lstat = fs.lstatSync(srcPath);
		fs.exists(destPath,function(exists) {
			if(exists) {
				
			} else {
				fs.mkdir(destPath,function() {
					var files = fs.readdirSync(srcPath);

					if(files.length > 0) {
						for(var i = 0;i<files.length;i++) {
							var filePath = srcPath + '/' + files[i];
							var newDestPath = destPath + '/' + files[i];
							if(fs.statSync(filePath).isFile()) {
								copyFile(filePath,newDestPath);
							} else {
								copyDir(filePath,newDestPath);		
							}
						}	
					}
				});
			}
		})
	} catch(err) {
		console.error(err);
		return;
	} 
}

function copyFile(file,destPath) {
	if(fs.statSync(file).isFile()) {
		var is = fs.createReadStream(file);
		var os = fs.createWriteStream(destPath);
		is.pipe(os);
	}
}
