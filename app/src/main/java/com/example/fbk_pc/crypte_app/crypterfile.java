package com.example.fbk_pc.crypte_app;

/**
 * Created by TM161 on 03/04/2017.
 */
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
public class crypterfile {





        String PASSWORD_FILE;
        public crypterfile(String PASSWORD_FILE){
            this.PASSWORD_FILE=PASSWORD_FILE;
        }

        public void EncryptionFile(String FileInputE) throws IOException, NoSuchAlgorithmException,
                NoSuchPaddingException, InvalidKeyException {

            FileInputStream fileinput = new FileInputStream(FileInputE);
            File fileE = new File(FileInputE);
            FileOutputStream fileoutput=new FileOutputStream(Environment.getExternalStorageDirectory()+"/StrBoxx/"+fileE.getName()+".ECrypt");
            SecretKeySpec key = new SecretKeySpec("MyDifficultPassw".getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(fileoutput, cipher);
            int b;
            byte[] d = new byte[8];
            while ((b = fileinput.read(d)) != -1){
                cipherOutputStream.write(d, 0x0, b);
            }
            cipherOutputStream.flush();
            cipherOutputStream.close();
            fileE.delete();
            fileinput.close();
        }

        public void DecryptionFile(String FileInputD,String fileoutput) throws IOException, NoSuchAlgorithmException,
                NoSuchPaddingException, InvalidKeyException{

            FileInputStream fileinputStream = new FileInputStream(FileInputD);
            File fileD = new File(FileInputD);
            String [] filename = fileD.getName().split("\\.ECrypt");
            FileOutputStream fileoutputStream = new FileOutputStream(fileoutput);
            SecretKeySpec key = new SecretKeySpec("MyDifficultPassw".getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            CipherInputStream cipherinputStream = new CipherInputStream(fileinputStream, cipher);
            int b;
            byte[] d = new byte[8];
            while ((b = cipherinputStream.read(d)) != -1){
                fileoutputStream.write(d, 0x0, b);
            }
            fileoutputStream.flush();
            fileoutputStream.close();
            fileD.delete();
            cipherinputStream.close();
        }
        public void EncryptionDirectory(String FileInputE)throws Exception{
            final File Folder_to_Zip = new File(FileInputE);
            final ZipOutputStream ZipFolder = new ZipOutputStream(new FileOutputStream(Environment.getExternalStorageDirectory()
                    +"/"+Folder_to_Zip.getName()+".zip"));
            CreateZipFolder(Folder_to_Zip, ZipFolder);
            ZipFolder.close();
            EncryptionFile(Environment.getExternalStorageDirectory()+"/"+Folder_to_Zip.getName()+".zip");
            Folder_to_Zip.delete();
        }
        public void DecryptionDirectory() throws Exception{

            String zipname = "data.zip";
            FileInputStream fis = new FileInputStream(zipname);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Unzipping: " + entry.getName());

                int size;
                byte[] buffer = new byte[2048];

                FileOutputStream fos = new FileOutputStream(entry.getName());
                BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

                while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
                    bos.write(buffer, 0, size);
                }
                bos.flush();
                bos.close();
            }
            zis.close();
            fis.close();

        }
        static void CreateZipFolder(File Folder, ZipOutputStream ZipFile) throws IOException {
            File[] files = Folder.listFiles();
            byte[] tmpBuf = new byte[1024];
            for (int i = 0; i < files.length; i++) {
                FileInputStream in = new FileInputStream(files[i].getAbsolutePath());
                ZipFile.putNextEntry(new ZipEntry(files[i].getAbsolutePath()));
                int len;
                while ((len = in.read(tmpBuf)) > 0) {
                    ZipFile.write(tmpBuf, 0, len);
                }
                ZipFile.closeEntry();
                in.close();
            }
        }


}
