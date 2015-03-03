package casia.isiteam.videosearch.common;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class FileRecver {
	
	public String genFileName(String sourceIp, String fileName, long fileSize){
		long time=new Date().getTime();
		StringBuilder sb=new StringBuilder();
		sb.append(sourceIp);
		sb.append(fileName);
		sb.append(fileSize);
		sb.append(time);
		sb.append(new Random().nextInt());
		
		return sb.toString();
	}
	
	public static void main(String[] args) {  
        try {  
            final ServerSocket server = new ServerSocket(48123);  
            Thread th = new Thread(new Runnable() {  
                public void run() {  
                    while (true) {  
                        try {    
                            System.out.println("��ʼ����...");  
                            /* 
                             * ���û�з��������Զ��ȴ� 
                             */  
                            Socket socket = server.accept();  
                            System.out.println("������");  
                            receiveFile(socket);  
                        } catch (Exception e) {  
                            System.out.println("�������쳣");  
                            e.printStackTrace();  
                        }  
                    }  
                }  
            });  
            th.run(); //�����߳�����  
        } catch (Exception e) {  
            e.printStackTrace();  
        }       
    }  
  
    public void run() {  
    }  
  
    /** 
     * �����ļ����� 
     * @param socket 
     * @throws IOException 
     */  
    public static void receiveFile(Socket socket) throws IOException {  
        byte[] inputByte = null;  
        int length = 0;  
        DataInputStream dis = null;  
        FileOutputStream fos = null;  
        String filePath = "D:/temp/"+GetDate.getDate()+"SJ"+new Random().nextInt(10000)+".zip";  
        try {  
            try {  
                dis = new DataInputStream(socket.getInputStream());  
                File f = new File("D:/temp");  
                if(!f.exists()){  
                    f.mkdir();    
                }  
                /*   
                 * �ļ��洢λ��   
                 */  
                fos = new FileOutputStream(new File(filePath));      
                inputByte = new byte[1024];     
                System.out.println("��ʼ��������...");    
                while ((length = dis.read(inputByte, 0, inputByte.length)) > 0) {  
                    fos.write(inputByte, 0, length);  
                    fos.flush();      
                }  
                System.out.println("��ɽ��գ�"+filePath);  
            } finally {  
                if (fos != null)  
                    fos.close();  
                if (dis != null)  
                    dis.close();  
                if (socket != null)  
                    socket.close();   
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    public static class GetDate {  
        /** 
         * ʱ���ʽ������ 
         */  
        private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
        public static String getDate(){  
            return df.format(new Date());  
        }    
    }  
    
}