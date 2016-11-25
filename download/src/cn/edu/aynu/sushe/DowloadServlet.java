package cn.edu.aynu.sushe;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class DowloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文件下载
		// 拿到我们的参数名
		String filename = request.getParameter("filename");
		// 处理一下文件名的中文乱码
//		filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

		// 拿到文件的真实目录（绝对路径）
		String path = this.getServletContext().getRealPath(
				"/WEB-INF/downloads/" + filename);
		// 判断文件属于什么类型
		String mimeType = this.getServletContext().getMimeType(filename);
		// 设置内容的类型
		response.setContentType(mimeType);

		// 设置下载方式 dispositio 处理
		response.setHeader("Content-Disposition", "attachment;filename="
				+ URLEncoder.encode(filename, "UTF-8"));

		// 使用文件输入流 读取数据
		FileInputStream fileInputStream = new FileInputStream(path);

		// 使用输出流将文件保存到客户端

		// 使用 commons-io 流中的
		IOUtils.copy(fileInputStream, response.getOutputStream());

		fileInputStream.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
