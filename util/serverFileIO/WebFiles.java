package serverFileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class WebFiles {
	private static String dirPath = System.getProperty("user.dir");
	private static String webPagesPath = dirPath + "\\WebPages";
	
	public static boolean IsURIlengthGood(String uri) {
		return ((webPagesPath + uri).length() < 255 ? true : false);
	}
	
	//finds the fully qualified path for a requested uri
	public static String FindFileByURI(String uri) throws Exception {
		String dirPath, fileName;
		//get the directory and find the file
		dirPath = webPagesPath + uri.substring(0,uri.lastIndexOf("/"));
		fileName = uri.substring(uri.lastIndexOf("/") + 1);
		
		File dir = new File(dirPath);
		File[] dirFiles = dir.listFiles();
		
		//check for name and extention match first
		for(int i = 0; i < dirFiles.length; i++) {
			//sift through the files in the directory
			if(dirFiles[i].isFile()) {
				//find the file requested
				if(dirFiles[i].getName().equals(fileName)) {				
					return dirFiles[i].getAbsolutePath();
				}
			}
		}
		//check for just filename match
		for(int i = 0; i < dirFiles.length; i++) {
			//sift through the files in the directory
			if(dirFiles[i].isFile()) {
				//find the file requested
				if(dirFiles[i].getName().substring(0, dirFiles[i].getName().lastIndexOf(".")).equals(fileName)) {				
					return dirFiles[i].getAbsolutePath();
				}
			}
		}
		//if it don't exist
		throw new Exception("Could not find File");
	}
	
	//reads file at fully qualified path.
	public static String Read(String path) throws IOException {
		String output = "";
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get(path));
			String line = null;
			do {
				line = reader.readLine();
				if(line != null) {
					output += line;
				}
			} while(line != null);
		} catch (IOException e1) {
			throw e1;
		}
		return output;
	}
	
	public static String FindAndRead(String uri) throws Exception {
		try {
			return Read(FindFileByURI(uri));
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static String GetContentType(String path) {
		path = path.substring(path.lastIndexOf("."));
		switch(path.toLowerCase()) {
		case ".aac":
			return "audio/aac";
		case ".abw":
			return "application/x-abiword";
		case ".arc":
			return "application/x-freearc";
		case ".avi":
			return "video/x-msvideo";
		case ".azw":
			return "application/vnd.amazon.ebook";
		case ".bin":
			return "application/octet-stream";
		case ".bmp":
			return "image/bmp";
		case ".bz":
			return "application/x-bzip";
		case ".bz2":
			return "application/x-bzip2";
		case ".csh":
			return "application/x-csh";
		case ".css":
			return "text/css";
		case ".csv":
			return "text/csv";
		case ".doc":
			return "application/msword";
		case ".docx":
			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		case ".eot":
			return "application/vnd.ms-fontobject";
		case ".epub":
			return "application/epub+zip";
		case ".gz":
			return "application/gzip";
		case ".gif":
			return "image/gif";
		case ".htm":
		case ".html":
			return "text/html";
		case ".ico":
			return "image/vnd.microsoft.icon";
		case ".ics":
			return "text/calendar";
		case ".jar":
			return "application/java-archive";
		case ".jpeg":
		case ".jpg":
			return "image/jpeg";
		case ".json":
			return "application/json";
		case ".jsonld":
			return "application/ld+json";
		case ".mid":
		case ".midi":
			return "audio/midi";
		case ".mjs":
			return "text/javascript";
		case ".mp3":
			return "audio/mpeg";
		case ".cda":
			return "application/x-cdf";
		case ".mp4":
			return "video/mp4";
		case ".mpeg":
			return "video/mpeg";
		case ".mpkg":
			return "application/vnd.apple.installer+xml";
		case ".odp":
			return "application/vnd.oasis.opendocument.presentation";
		case ".ods":
			return "application/vnd.oasis.opendocument.spreadsheet";
		case ".odt":
			return "application/vnd.oasis.opendocument.text";
		case ".oga":
			return "audio/ogg";
		case ".ogv":
			return "video/ogg";
		case ".ogx":
			return "application/ogg";
		case ".opus":
			return "audio/opus";
		case ".otf":
			return "font/otf";
		case ".png":
			return "image/png";
		case ".pdf":
			return "application/pdf";
		case ".php":
			return "application/x-httpd-php";
		case ".ppt":
			return "application/vnd.ms-powerpoint";
		case ".pptx":
			return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
		case ".rar":
			return "application/vnd.rar";
		case ".rtf":
			return "application/rtf";
		case ".sh":
			return "application/x-sh";
		case ".svg":
			return "image/svg+xml";
		case ".swf":
			return "application/x-shockwave-flash";
		case ".tar":
			return "application/x-tar";
		case ".tif":
		case ".tiff":
			return "image/tiff";
		case ".ts":
			return "video/mp2t";
		case ".ttf":
			return "font/ttf";
		case ".txt":
			return "text/plain";
		case ".vsd":
			return "application/vnd.visio";
		case ".wav":
			return "audio/wav";
		case ".weba":
			return "audio/webm";
		case ".webm":
			return "video/webm";
		case ".webp":
			return "image/webp";
		case ".woff":
			return "font/woff";
		case ".woff2":
			return "font/woff2";
		case ".xhtml":
			return "application/xhtml+xml";
		case ".xls":
			return "application/vnd.ms-excel";
		case ".xlsx":
			return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		case ".xml":
			//revisit later if needed
			return "text/xml";
		case ".xul":
			return "application/vnd.mozilla.xul+xml";
		case ".zip":
			return "application/zip";
		case ".7z":
			return "application/x-7z-compressed";
		case ".js":
			return "application/javascript; charset=utf-8";
		default:
			return "";
		}
	}
}
