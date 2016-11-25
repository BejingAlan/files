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
	 * ���ǲ���ͨ���򵥵�getparamter ����ȡ�ı����е����� fileupload ��һ����Դ�� ��������������������
	 */

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// [1]������������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

		ServletFileUpload fileUpload = new ServletFileUpload(
				diskFileItemFactory);

		// ���������������������
		fileUpload.setHeaderEncoding("UTF-8");
		// �����ϴ������ļ��Ĵ�С
		fileUpload.setFileSizeMax(1024 * 1024); // 1MB
		try {
			// ʹ�ý���������request,�õ�����fileItem���������
			List<FileItem> parseRequest = fileUpload.parseRequest(request);
			for (FileItem fileItem : parseRequest) {
				// �ж��ǲ���һ����ͨ�ı� field Ӣ����˼ �� ����
				if (fileItem.isFormField()) {

					String fieldName = fileItem.getFieldName();

					if (fieldName.equals("username")) {
						// �����ϴ���
						response.getWriter().print(
								"�û���" + fileItem.getString("UTF-8") + "<br>");
					}

				} else {
					// ��һ���ļ�
					String name = fileItem.getName();
					int lastIndexOf = name.lastIndexOf("\\");
					String substring = name.substring(lastIndexOf + 1);
					name = substring;
					System.out.println(substring);

					if (name == null || name.isEmpty()) {
						// ��������ѭ����������һ��ѭ��
						continue;
					}
					// �����ϴ��ļ������·����һ�㶼���� WEB-INF/ Ŀ¼�£�����ͨ��������ֱ�ӷ���
					// ��ȡ�ϴ��ļ��������ʵ·��������·�������ϴ����ļ������Ŀ¼
					String savepath = this.getServletContext().getRealPath(
							"/WEB-INF/fileupload");
					// ��uploadsĿ¼�´�������ļ��У���ÿ���ļ��д�������ļ�
					int hashcode = name.hashCode();
					System.out.println(hashcode);
					// ��ȡhashcode�ĵ�4λ����ת����16�����ַ���
					String dir1 = Integer.toHexString(hashcode & 0XF);
					System.out.println(dir1);
					// ��ȡhashcode��5~8λ����ת����16�����ַ���
					String dir2 = Integer.toHexString(hashcode >>> 4 & 0XF);
					System.out.println(dir2);
					// ���ļ�Ŀ¼�ϲ���������·��
					savepath = savepath + "/" + dir1 + "/" + dir2;
					// ��������ڣ��ʹ����ļ���
					new File(savepath).mkdirs();
					System.out.println(savepath);

					// Ϊ��֤�������ظ��� �ļ��� ʹ��UUID ����ƴ name
					String uuidname = UUID.randomUUID().toString();
					// ��ƴ�µ��ļ���
					String filename = uuidname + "_" + name;
					// ��װ�ļ�
					File file = new File(savepath, filename);
					// �ϴ��ļ�
					fileItem.write(file);

					// ���ؿͻ�����ʾ
					response.getWriter().print("�ϴ��ļ���" + name + "<br>");
					response.getWriter().print(
							"�ϴ��ļ��Ĵ�С" + fileItem.getSize() + "<br>");
					response.getWriter().print(
							"�ϴ��ļ�������" + fileItem.getContentType() + "<br>");

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
