package com.spring.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;


// import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil
{

	// public static Logger log = Logger.getLogger("com.spring.util.FileUtil");
	public static Logger log = LoggerFactory.getLogger("com.spring.util.FileUtil");

	public static String getUserDataDir(final String userPath, final String rootname)
	{
		return userPath + "/" + rootname;
	}

	public static String getUserDataDir(final String userPath, final String rootname, final String subpath)
	{
		return getUserDataDir(userPath, rootname) + "/" + subpath;
	}

	
	
	public static boolean makeDir(final String path)
	{
		final File dir = new File(path.replace("\\","/"));

		try 
		{
			if(!dir.exists())
			{
				if(dir.mkdirs())
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteDir(File path)
	{
//		if(!path.exists())
//		{
//			return false;
//		}

		if (path.isDirectory())
		{
			File[] files = path.listFiles();
			for (File file : files)
			{
				if (file.isDirectory())
				{
					deleteDir(file);
				}
				else
				{
					file.delete();
				}
			}
			path.delete();
		}
		else
		{
			String p = path.toString()+".album.jpg";
			File f = new File(p);
			f.delete();

			f = null;
			p = path.toString()+".thumb.jpg";
			f = new File(p);
			f.delete();

			f = null;
			p = path.toString();
			f = new File(p);
			f.delete();
		}
//		return path.delete();
		return true;
	}


	public static void imageResize(int w, int h, String originFileName, String thumbFileName )
	{
		try
		{
			int thumbnail_width = w; 	//썸네일 가로사이즈
			int thumbnail_height = h; 	//썸네일 세로사이즈

			//원본이미지파일의 경로+파일명
			File origin_file_name = new File(originFileName);

			//생성할 썸네일파일의 경로+썸네일파일명
			File thumb_file_name = new File(thumbFileName);

			BufferedImage buffer_original_image = ImageIO.read(origin_file_name);
			BufferedImage buffer_thumbnail_image = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D graphic = buffer_thumbnail_image.createGraphics();
			graphic.drawImage(buffer_original_image, 0, 0, thumbnail_width, thumbnail_height, null);
			ImageIO.write(buffer_thumbnail_image, "jpg", thumb_file_name);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static boolean copyDir(File copyDirectory, File targetDirectory)
	{
		try
		{
	        // 해당 경로가 디렉토리인 경우
			if (copyDirectory.isDirectory())
			{
				if(!targetDirectory.exists())
				{
					targetDirectory.mkdir();
				}

				String[] children = copyDirectory.list();
				for (int i=0; i<children.length; i++)
				{
					copyDir(new File(copyDirectory, children[i]),new File(targetDirectory, children[i]));
		        }
			}
			else  // 해당 경로가 파일경로인 경우
			{
				try
				{
					FileInputStream fis = new FileInputStream(copyDirectory);
					BufferedInputStream bis = new BufferedInputStream(fis, 1024);

					FileOutputStream fos = new FileOutputStream(targetDirectory);
					BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);

					int len= 0; byte[] buf = new byte[1024];

					while((len = bis.read(buf)) != -1)
					{
						bos.write(buf, 0, len);
						bos.flush();
					}

				}
				catch(FileNotFoundException fn)
				{
					fn.printStackTrace();
					return false;

				}
				catch(IOException ie)
				{
					ie.printStackTrace();
					return false;
				}
			}

			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
    }



	public static boolean saveFile(InputStream uploadedInputStream, String serverLocation)
	{

		boolean r = false;

		try
		{
			OutputStream outputStream = null;
			int read = 0;
			byte[] bytes = new byte[1024];

			outputStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1)
			{
				outputStream.write(bytes, 0, read);
			}

			outputStream.flush();
			outputStream.close();

			uploadedInputStream.close();
			r = true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{

		}

		return r ;
	}


	public static boolean fileDownload(File downloadFile, HttpServletResponse res ) throws Exception
	{
		boolean r = false;

		if(!downloadFile.exists())
		{
            throw new FileNotFoundException();
        }

		OutputStream outStream = null;
        FileInputStream inputStream = null;

        try
        {
            outStream = res.getOutputStream();
            inputStream = new FileInputStream(downloadFile);

            //Setting Resqponse Header

            res.setContentLength((int) downloadFile.length());

            String headerKey = "Content-Disposition";
		    String headerValue = String.format("attachment; filename=\"%s\"", URLEncoder.encode(downloadFile.getName(),"utf-8"));

		    res.setContentType("application/octet-stream");
		    //res.setContentType("application/json");

		    res.setHeader(headerKey, headerValue);

            //Writing InputStream to OutputStream
            byte[] outByte = new byte[4096];
            while(inputStream.read(outByte, 0, 4096) != -1)
            {
              outStream.write(outByte, 0, 4096);
            }

            r = true;

        }
        catch (Exception e)
        {
        	throw new IOException();
        }
        finally
        {
        	inputStream.close();
        	outStream.flush();
        	outStream.close();
        }

		return r;
	}

	public static void getFileDownload(File downloadFile, HttpServletResponse res ) throws Exception
	{

		//System.out.println("getFileDownload start");
		if(!downloadFile.exists())
		{
            throw new FileNotFoundException();
        }

		OutputStream outStream = null;
        FileInputStream inputStream = null;

        try
        {
            outStream = res.getOutputStream();
            inputStream = new FileInputStream(downloadFile);

            //Setting Resqponse Header

            res.setContentLength((int) downloadFile.length());

            String headerKey = "Content-Disposition";
		    String headerValue = String.format("attachment; filename=\"%s\"", URLEncoder.encode(downloadFile.getName(),"utf-8"));

		    res.setContentType("application/octet-stream");
		    res.setHeader(headerKey, headerValue);

            //Writing InputStream to OutputStream
            byte[] outByte = new byte[4096];
            while(inputStream.read(outByte, 0, 4096) != -1)
            {
              outStream.write(outByte, 0, 4096);
            }


        }
        catch (Exception e)
        {
        	throw new IOException();
        }
        finally
        {
        	inputStream.close();
        	outStream.flush();
        	outStream.close();
        }


	}

//	public static void getThumb2(String videoFilename, String thumbFilename, int width, int height,int hour, int min, float sec)
//    throws IOException, InterruptedException
//{
//  ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y", "-i", videoFilename, "-vframes", "1",
//      "-ss", hour + ":" + min + ":" + sec, "-f", "mjpeg", "-s", width + "*" + height, "-an", thumbFilename);
//  Process process = processBuilder.start();
//  InputStream stderr = process.getErrorStream();
//  InputStreamReader isr = new InputStreamReader(stderr);
//  BufferedReader br = new BufferedReader(isr);
//  String line;
//  while ((line = br.readLine()) != null);
//  process.waitFor();
//}

	public static void ffmpegImgResize(String input, String output, String w, String h )
	{
		try
		{
			Runtime run = Runtime.getRuntime();
			String command = "ffmpeg -i "+input+" -vf scale="+w+":"+h+ " " +output;

			log.debug("ffmpegImgResize command: "+command);
			try{
			    run.exec(command);
			}catch(Exception e){
			    System.out.println("error : "+e.getMessage());
			    e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			System.out.println("ffmpegResize error >>>> "+e.toString());
		}
	}

	public static boolean ffmpegMakeThumb(String videoFilePath, int w, int h, String outPutImgPath, String userid, String filename)
	{
		boolean r = false;

		Process process = null;
		Runtime run = Runtime.getRuntime();
		String command = "ffmpeg -i "+videoFilePath+" -r 1 -ss 00:00:05 -s "+w+"*"+h+" -vframes 1 -f image2 "+outPutImgPath;
		//log.debug("ffmpegMakeThumb command: "+command);
		try
		{
		    process = run.exec(command);
		    process.waitFor();
		    r = true;

		}
		catch(Exception e)
		{
		    System.out.println("error : "+e.getMessage());
		    e.printStackTrace();
		}

		process.destroy();

		log.info("thumbnail ffmpeg file create success !!! :: " + userid +"/"+filename);
		
		return r;
	}

	public static boolean fileCopy(String inFileName, String outFileName)
	{
		boolean r = false;
		try
		{
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);

			int data = 0;
			while((data=fis.read())!=-1)
			{
				fos.write(data);
			}
			fis.close();
			fos.close();

			r = true;

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return r;
	}


	public static boolean isSupportedVideoPlayExtName(String fileext)
	{
		fileext = fileext.toLowerCase();
		if(fileext.equals("mp4") || fileext.equals("wmv")
				|| fileext.equals("flv") || fileext.equals("webm")) return true;
		return false;
	}


	public static boolean renameFile(File file, File file2, String path, String rePath, String name, String type)
	{
		String p = "";
		String p2 = "";
		if(file.renameTo(file2))
		{
			p = path +".album.jpg";
			p2 = rePath+name+"."+type+".album.jpg";
			System.out.println("1 p ==> " + p);
			System.out.println("1 p2 ==> " + p2);
			File f  = new File(p);
			File f2 = new File(p2);
			if(f.renameTo(f2))
			{
				p = path +".thumb.jpg";
				p2 = rePath+name+"."+type+".thumb.jpg";
				System.out.println("2 p ==> " + p);
				System.out.println("2 p2 ==> " + p2);
				f  = new File(p);
				f2 = new File(p2);

				if(f.renameTo(f2))
				{
					return true;
				}
				else
				{
					p = path +".album.jpg";
					p2 = rePath+name+"."+type+".album.jpg";
					f  = new File(p);
					f2 = new File(p2);
					f2.renameTo(f);
					file2.renameTo(file);
					return false;
				}
			}
			else
			{
				file2.renameTo(file);
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public static long folderSize(File directory)
	{
		long length = 0;
		for (File file : directory.listFiles())
		{
			if (file.isFile())
				length += file.length();
			else
				length += folderSize(file);
		}
		return length;
	}

}
