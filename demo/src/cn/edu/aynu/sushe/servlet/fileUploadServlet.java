package cn.edu.aynu.sushe.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class fileUploadServlet extends HttpServlet {

	/**
	 * 我们不能通过简单的getparamter 来获取文本域中的内容 fileupload 是一个开源的 用来解析二进制数据流
	 */

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// [1]处理中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

		ServletFileUpload fileUpload = new ServletFileUpload(
				diskFileItemFactory);

		// 解决中文名中文乱码问题
		fileUpload.setHeaderEncoding("UTF-8");
		// 设置上传单个文件的大小
		fileUpload.setFileSizeMax(1024 * 1024); // 1MB
		try {
			// 使用解析器解析request,得到包含fileItem对象的链表
			List<FileItem> parseRequest = fileUpload.parseRequest(request);
			for (FileItem fileItem : parseRequest) {
				// 判断是不是一个普通文本 field 英文意思 ： 人名
				if (fileItem.isFormField()) {

					String fieldName = fileItem.getFieldName();

					if (fieldName.equals("username")) {
						// 返回上传者
						response.getWriter().print(
								"用户名" + fileItem.getString("UTF-8") + "<br>");
					}

				} else {
					// 是一个文件
					String name = fileItem.getName();
					int lastIndexOf = name.lastIndexOf("\\");
					String substring = name.substring(lastIndexOf + 1);
					name = substring;
					System.out.println(substring);

					if (name == null || name.isEmpty()) {
						// 结束本次循环，进行下一次循环
						continue;
					}
					// 设置上传文件保存的路径，一般都放在 WEB-INF/ 目录下，不能通过服务器直接访问
					// 获取上传文件保存的真实路径（绝对路径），上传的文件保存的目录
					String savepath = this.getServletContext().getRealPath(
							"/WEB-INF/fileupload");
					// 在uploads目录下创建多个文件夹，让每个文件夹存放少量文件
					int hashcode = name.hashCode();
					System.out.println(hashcode);
					// 获取hashcode的低4位，并转换成16进制字符串
					String dir1 = Integer.toHexString(hashcode & 0XF);
					System.out.println(dir1);
					// 获取hashcode的5~8位，并转换成16进制字符串
					String dir2 = Integer.toHexString(hashcode >>> 4 & 0XF);
					System.out.println(dir2);
					// 与文件目录合并构成完整路径
					savepath = savepath + "/" + dir1 + "/" + dir2;
					// 如果不存在，就创建文件夹
					new File(savepath).mkdirs();
					System.out.println(savepath);

					// 为保证产生不重复的 文件名 使用UUID 来组拼 name
					String uuidname = UUID.randomUUID().toString();
					// 组拼新的文件名
					String filename = uuidname + "_" + name;
					// 封装文件
					File file = new File(savepath, filename);
					// 上传文件
					fileItem.write(file);

					// 返回客户端提示
					response.getWriter().print("上传文件名" + name + "<br>");
					response.getWriter().print(
							"上传文件的大小" + fileItem.getSize() + "<br>");
					response.getWriter().print(
							"上传文件的类型" + fileItem.getContentType() + "<br>");

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
