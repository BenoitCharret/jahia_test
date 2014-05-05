package com.test.staticfile;

import org.drools.spi.KnowledgeHelper;
import org.jahia.services.content.rules.PublishedNodeFact;

import javax.jcr.RepositoryException;
import java.io.*;


/**
 * Created by benoit on 05/05/14.
 */
public class StaticFileService {

    public void copyStatic(PublishedNodeFact node,KnowledgeHelper drools) throws RepositoryException{
        System.out.println("a node is published "+node.getNode().getPath());
        System.out.println("a url is published "+node.getNode().getUrl());
        InputStream inputStream=null;
        OutputStream outputStream =null;
        try{
            System.out.println("creation du fichier sur disque");
        inputStream=node.getNode().getFileContent().downloadFile();

            // write the inputStream to a FileOutputStream


            File file = new File("/tmp/jahia" + node.getNode().getUrl());
            File parentDir=file.getParentFile();
            parentDir.mkdirs();

            file.createNewFile();
            outputStream =
                    new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            System.out.println("Done!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
