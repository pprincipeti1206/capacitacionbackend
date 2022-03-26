package com.incloud.hcp.service._framework.cmis.impl;


import com.incloud.hcp.cmis.CmisFile;
import com.incloud.hcp.exception.ServiceException;
import com.incloud.hcp.service._framework.cmis.CmisService;
import com.sap.ecm.api.EcmService;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisNameConstraintViolationException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrador on 26/09/2017.
 */
@Profile("!devlocal")
@Component("cmisService")
public class CmisServiceImpl implements CmisService {

    private final String LOOKUP_NAME = "java:comp/env/EcmService";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${sm.portal.dev}")
    private Boolean isDev;

    @Value("${sm.portal.cmis.name}")
    private String repositoryName;

    @Value("${sm.portal.cmis.key}")
    private String repositoryKey;

    @Override
    public CmisFile createDocumento(String folderId, MultipartFile file) {
        try {
            logger.error("createDocumento ingresando 0: " );
            InitialContext ctx = new InitialContext();
            EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);
            logger.error("createDocumento ingresando 1 :  repositoryName " + repositoryName);
            logger.error("createDocumento ingresando 2 :  repositoryKey " + repositoryKey);
            Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);
            logger.error("createDocumento ingresando 3");

            if (!openCmisSession.exists(folderId)) {
                throw new ServiceException("El folder Id no existe");
            }

            String extension = Optional.ofNullable(file.getOriginalFilename())
                    .map(s -> s.split("\\."))
                    .filter(s -> s.length > 0)
                    .map(s -> "." + s[s.length - 1])
                    .orElse("");
            logger.error("createDocumento 00 extension: " + extension);

            String fileNewName = file.getOriginalFilename().replace(extension, "") /*+
                    "-" + System.currentTimeMillis() +
                    extension*/;
            logger.error("createDocumento 01 fileNewName: " + fileNewName);

            //***********************************************
            String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ";
            // Cadena de caracteres ASCII que reemplazarán los originales.
            String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy";
            //String nombreFinal =fileNewName;
            for (int i = 0; i < original.length(); i++) {
                // Reemplazamos los caracteres especiales.
                fileNewName = fileNewName.replace(original.charAt(i), ascii.charAt(i));
            }
            logger.error("createDocumento 02 fileNewName: " + fileNewName);
            fileNewName = fileNewName.replaceAll("[^a-zA-Z0-9]", "");
            logger.error("createDocumento 03 fileNewName: " + fileNewName);
            logger.debug("createDocumento 04: " + extension + " fileName: " + fileNewName);
            fileNewName = fileNewName +
                    "-" + System.currentTimeMillis() +
                    extension;
            //fileNewName = fileNewName + extension;
            //***********************************************

            Folder folder = (Folder) openCmisSession.getObject(folderId);
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
            properties.put(PropertyIds.NAME, fileNewName);
            properties.put(PropertyIds.DESCRIPTION, "NO REGISTRADO");
            //properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, Collections.singletonList("custom:registrado"));
            //properties.put("registrado", Boolean.FALSE);

            InputStream stream = new ByteArrayInputStream(file.getBytes());


            ContentStream contentStream = openCmisSession.getObjectFactory()
                    .createContentStream(fileNewName, file.getSize(), file.getContentType(), stream);

            Document doc = folder.createDocument(properties, contentStream, VersioningState.NONE);
            CmisFile cmisFile = new CmisFile();
            cmisFile.setId(doc.getId());
            cmisFile.setName(file.getOriginalFilename());
            cmisFile.setType(file.getContentType());
            cmisFile.setSize(file.getSize());

            StringBuilder path = new StringBuilder("/");
            path.append(openCmisSession.getRootFolder().getId());
            path.append("/root/");
            path.append(folder.getName());
            path.append("/");
            path.append(fileNewName);
            cmisFile.setUrl(path.toString());

            return cmisFile;
        } catch (NamingException ex) {
            ex.printStackTrace();
            throw new ServiceException("Error de conexión al repositorio");
        } catch (CmisNameConstraintViolationException ex) {
            ex.printStackTrace();
            throw new ServiceException("Existe un archivo con el mismo nombre");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ServiceException("Error al leer");
        }
    }

    @Override
    public CmisFile createOrReplaceDocumento(String folderId, MultipartFile file) throws Exception{
        String fileNewNameBackup = "";
        InitialContext ctx = new InitialContext();
        EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);
        logger.error("createOrReplaceDocumento ingresando 1 :  repositoryName " + repositoryName);
        logger.error("createOrReplaceDocumento ingresando 2 :  repositoryKey " + repositoryKey);

        Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);
        logger.error("createOrReplaceDocumento ingresando 3");
        try {

            if (!openCmisSession.exists(folderId)) {
                throw new ServiceException("El folder Id no existe");
            }

            String extension = Optional.ofNullable(file.getOriginalFilename())
                    .map(s -> s.split("\\."))
                    .filter(s -> s.length > 0)
                    .map(s -> "." + s[s.length - 1])
                    .orElse("");
            logger.error("createDocumento 00 extension: " + extension);

            String fileNewName = file.getOriginalFilename().replace(extension, "") /*+
                    "-" + System.currentTimeMillis() +
                    extension*/;
            logger.error("createDocumento 01 fileNewName: " + fileNewName);

            //***********************************************
            String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ";
            // Cadena de caracteres ASCII que reemplazarán los originales.
            String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy";
            //String nombreFinal =fileNewName;
            for (int i = 0; i < original.length(); i++) {
                // Reemplazamos los caracteres especiales.
                fileNewName = fileNewName.replace(original.charAt(i), ascii.charAt(i));
            }
            logger.error("createDocumento 02 fileNewName: " + fileNewName);
            fileNewName = fileNewName.replaceAll("[^a-zA-Z0-9]", "");
            logger.error("createDocumento 03 fileNewName: " + fileNewName);
            logger.debug("createDocumento 04: " + extension + " fileName: " + fileNewName);
//            fileNewName = fileNewName +
//                    "-" + System.currentTimeMillis() +
//                    extension;
            //fileNewNameBackup = fileNewName + "_01" + extension;
            fileNewName = fileNewName + extension;
            fileNewNameBackup = fileNewName;
            //***********************************************

            Folder folder = (Folder) openCmisSession.getObject(folderId);
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
            properties.put(PropertyIds.NAME, fileNewName);
            properties.put(PropertyIds.DESCRIPTION, "NO REGISTRADO");
            InputStream stream = new ByteArrayInputStream(file.getBytes());
            ContentStream contentStream = openCmisSession.getObjectFactory()
                    .createContentStream(fileNewName, file.getSize(), file.getContentType(), stream);

            Document doc = folder.createDocument(properties, contentStream, VersioningState.NONE);
            CmisFile cmisFile = new CmisFile();
            cmisFile.setId(doc.getId());
            cmisFile.setName(file.getOriginalFilename());
            cmisFile.setType(file.getContentType());
            cmisFile.setSize(file.getSize());

            StringBuilder path = new StringBuilder("/");
            path.append(openCmisSession.getRootFolder().getId());
            path.append("/root/");
            path.append(folder.getName());
            path.append("/");
            path.append(fileNewName);
            cmisFile.setUrl(path.toString());

            return cmisFile;
        } catch (CmisNameConstraintViolationException ex) {

            List<CmisFile> cmisFileList = this.getListFileByFolderId(folderId);
            for(CmisFile beanFile: cmisFileList) {
                if (beanFile.getName().equals(fileNewNameBackup)) {
                    beanFile.setType(file.getContentType());
                    beanFile.setSize(file.getSize());

                    StringBuilder path = new StringBuilder("/");
                    path.append(openCmisSession.getRootFolder().getId());
                    path.append("/root");
                    path.append(beanFile.getUrl());
                    beanFile.setUrl(path.toString());
                    return beanFile;
                }
            }
            return null;

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }
    }

    public CmisFile createOrReplaceDocumentoStream(
            String folderId,
            String nameFile,
            Long sizeFile,
            String typeFile,
            InputStream stream) throws Exception{
        String fileNewNameBackup = "";
        InitialContext ctx = new InitialContext();
        EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);
        //logger.error("createOrReplaceDocumento ingresando 1 :  repositoryName " + repositoryName);
        //logger.error("createOrReplaceDocumento ingresando 2 :  repositoryKey " + repositoryKey);

        Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);
        //logger.error("createOrReplaceDocumento ingresando 3");
        try {

            if (!openCmisSession.exists(folderId)) {
                throw new ServiceException("El folder Id no existe");
            }

            String extension = Optional.ofNullable(nameFile)
                    .map(s -> s.split("\\."))
                    .filter(s -> s.length > 0)
                    .map(s -> "." + s[s.length - 1])
                    .orElse("");
            logger.error("createDocumento 00 extension: " + extension);

            String fileNewName = nameFile.replace(extension, "");
            //String fileNewName = nameFile;
            //logger.error("createDocumento 01 fileNewName: " + fileNewName);

            //***********************************************
            String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ";
            // Cadena de caracteres ASCII que reemplazarán los originales.
            String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy";
            //String nombreFinal =fileNewName;
            for (int i = 0; i < original.length(); i++) {
                // Reemplazamos los caracteres especiales.
                fileNewName = fileNewName.replace(original.charAt(i), ascii.charAt(i));
            }
            //logger.error("createDocumento 02 fileNewName: " + fileNewName);
            fileNewName = fileNewName.replaceAll("[^a-zA-Z0-9]", "");
            //logger.error("createDocumento 03 fileNewName: " + fileNewName);
            //logger.debug("createDocumento 04: " + extension + " fileName: " + fileNewName);
//            fileNewName = fileNewName +
//                    "-" + System.currentTimeMillis() +
//                    extension;
            //fileNewNameBackup = fileNewName + "_01" + extension;
            fileNewName = fileNewName + extension;
            fileNewNameBackup = fileNewName;
            //***********************************************

            Folder folder = (Folder) openCmisSession.getObject(folderId);
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
            properties.put(PropertyIds.NAME, fileNewName);
            properties.put(PropertyIds.DESCRIPTION, "NO REGISTRADO");
            //InputStream stream = new ByteArrayInputStream(bytesFile);
            ContentStream contentStream = openCmisSession.getObjectFactory()
                    .createContentStream(fileNewName, sizeFile, typeFile, stream);

            Document doc = folder.createDocument(properties, contentStream, VersioningState.NONE);
            CmisFile cmisFile = new CmisFile();
            cmisFile.setId(doc.getId());
            cmisFile.setName(nameFile);
            cmisFile.setType(typeFile);
            cmisFile.setSize(sizeFile);

            StringBuilder path = new StringBuilder("/");
            path.append(openCmisSession.getRootFolder().getId());
            path.append("/root/");
            path.append(folder.getName());
            path.append("/");
            path.append(fileNewName);
            cmisFile.setUrl(path.toString());

            return cmisFile;
        } catch (CmisNameConstraintViolationException ex) {

            List<CmisFile> cmisFileList = this.getListFileByFolderId(folderId);
            for(CmisFile beanFile: cmisFileList) {
                if (beanFile.getName().equals(fileNewNameBackup)) {
                    beanFile.setType(typeFile);
                    beanFile.setSize(sizeFile);

                    StringBuilder path = new StringBuilder("/");
                    path.append(openCmisSession.getRootFolder().getId());
                    path.append("/root");
                    path.append(beanFile.getUrl());
                    beanFile.setUrl(path.toString());
                    return beanFile;
                }
            }
            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }
    }



    @Override
    public String createFolder(String nameFolder) throws ServiceException {
        try {
            logger.debug("Creando una carpeta");
            if (nameFolder.trim().isEmpty()) {
                logger.error("El nombre del folder esta vacia");
                throw new ServiceException("El nombre del folder esta vacía");
            }

            InitialContext ctx = new InitialContext();
            EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);

            Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);

            Folder root = openCmisSession.getRootFolder();

            ItemIterable<CmisObject> children = root.getChildren();
            for (CmisObject cmisObject : children) {
                if (cmisObject.getName().equals(nameFolder)) {
                    logger.debug("La carpeta " + nameFolder + " existe!");
                    return cmisObject.getId();
                }
            }

            Map<String, String> newFolderProps = new HashMap<>();
            newFolderProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
            newFolderProps.put(PropertyIds.NAME, nameFolder);

            Folder newFolder = root.createFolder(newFolderProps);
            logger.debug("Se ha creado un folder en " + newFolder.getPath());
            return newFolder.getId();
        } catch (NamingException ex) {
            logger.error("Error de conexión al repositorio");
            throw new ServiceException("Error de conexión al repositorio");
        } catch (CmisNameConstraintViolationException ex) {
            logger.error("El folder ya existe");
            throw new ServiceException("El folder ya existe");
        }
    }

    @Override
    public void deleteFile(String archivoId) {
        try {
            InitialContext ctx = new InitialContext();
            EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);
            logger.info("Eliminar el archivo " + archivoId);
            Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);
            Document doc = (Document) openCmisSession.getObject(archivoId);
            doc.delete(true);
        } catch (Exception ex) {
            logger.error("Error al adjuntar archivo", ex);
            throw new ServiceException("Error al eliminar archivo");
        }
    }

    @Override
    public boolean folderExist(String folderId) {
        try {
            logger.debug("Folder exists : [ " + folderId + " ]");
            if (folderId.trim().isEmpty()) {
                logger.debug("El folder ID esta en blanco");
                return false;
            }
            InitialContext ctx = new InitialContext();
            EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);
            Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);
            return openCmisSession.exists(folderId);
        } catch (NamingException ex) {
            logger.error("Error de conexión al repositorio");
            throw new ServiceException("Error de conexión al repositorio");
        }
    }

    @Override
    public List<CmisFile> getListFileByFolderId(String folderId) {
        try {
            InitialContext ctx = new InitialContext();
            EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);
            Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);

            if (!openCmisSession.exists(folderId)) {
                throw new ServiceException("El folder Id no existe");
            }

            Folder folder = (Folder) openCmisSession.getObject(folderId);
            List<CmisFile> list = new ArrayList<>();

            folder.getChildren().forEach(cmiObject -> {
                CmisFile cmisFile = new CmisFile();
                cmisFile.setId(cmiObject.getId());
                cmisFile.setName(cmiObject.getName());
                cmisFile.setUrl(folder.getPath() + "/" + cmiObject.getName());
                Optional.ofNullable(cmiObject.getType()).map(type -> type.getDescription())
                        .ifPresent(cmisFile::setType);
                //cmisFile.setSize(cmiObject.getSize());
                list.add(cmisFile);
            });

            return list;
        } catch (NamingException ex) {
            throw new ServiceException("Error de conexión al repositorio");
        }
    }

    @Override
    public void updateFileLastVersionTrue(List<String> filesId) {

        try {
            logger.error("Actualización de version del archivo");
            InitialContext ctx = new InitialContext();
            EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);

            Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);
            Optional.ofNullable(filesId)
                    .ifPresent(l -> l.stream()
                            .peek(id -> logger.error("Actualizando el archivo id " + id))
                            .filter(openCmisSession::exists)
                            .forEach(id -> {
                                Document doc = (Document) openCmisSession.getObject(id);
                                Map<String, Object> properties = new HashMap<>();
                                properties.put(PropertyIds.DESCRIPTION, "REGISTRADO");
                                doc.updateProperties(properties, true);
                            }));

        } catch (Exception ex) {
            logger.error("Error al actualizar la version de los archivos", ex);
            throw new ServiceException("Error al actualizar la vserion de los archivos");
        }
    }

    @Override
    public List<CmisFile> updateFileAndMove(List<CmisFile> files, String folderId) {
        try {
            logger.error("Actualización de version del archivo");
            InitialContext ctx = new InitialContext();
            EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);

            Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);

            List<CmisFile> newFiles = new ArrayList<CmisFile>();
            Optional.ofNullable(files)
                    .ifPresent(l -> l.stream()
                            .peek(item -> logger.error("Actualizando el archivo id " + item.getId()))
                            .filter(item -> openCmisSession.exists(item.getId()))
                            .forEach(item -> {
                                FileableCmisObject object = (FileableCmisObject) openCmisSession.getObject(item.getId());
                                CmisObject sourceFolderId = object.getParents().get(0);
                                CmisObject targetFolderId = openCmisSession.getObject(folderId);
                                Folder folder = (Folder) openCmisSession.getObject(folderId);

                                CmisObject fileMove = object.move(sourceFolderId, targetFolderId);

                                StringBuilder path = new StringBuilder("/");
                                path.append(openCmisSession.getRootFolder().getId());
                                path.append("/root/");
                                path.append(folder.getName());
                                path.append("/");
                                path.append(fileMove.getName());
                                item.setUrl(path.toString());

                                newFiles.add(item);
                                logger.error("ARCHIVO MOVIDO !!!: ");
                                Map<String, Object> properties = new HashMap<>();
                                properties.put(PropertyIds.DESCRIPTION, "REGISTRADO");
                                fileMove.updateProperties(properties, true);
                            }));
            return newFiles;
        } catch (Exception ex) {
            logger.error("Error al actualizar la version de los archivos", ex);
            throw new ServiceException("Error al actualizar la version de los archivos");
        }

    }

    @Override
    public List<CmisFile> updateFileAndMoveVerificar(List<CmisFile> files, String folderId) {
        try {
            logger.error("Actualización de version del archivo");
            InitialContext ctx = new InitialContext();
            EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);

            Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);

            List<CmisFile> newFiles = new ArrayList<CmisFile>();
            Optional.ofNullable(files)
                    .ifPresent(l -> l.stream()
                            .peek(item -> logger.error("Actualizando el archivo id " + item.getId()))
                            .filter(item -> openCmisSession.exists(item.getId()))
                            .forEach(item -> {
                                FileableCmisObject object = (FileableCmisObject) openCmisSession.getObject(item.getId());
                                CmisObject sourceFolderId = object.getParents().get(0);
                                CmisObject targetFolderId = openCmisSession.getObject(folderId);
                                Folder folder = (Folder) openCmisSession.getObject(folderId);

                                if (!sourceFolderId.getId().equals(targetFolderId.getId())) {

                                    CmisObject fileMove = object.move(sourceFolderId, targetFolderId);

                                    StringBuilder path = new StringBuilder("/");
                                    path.append(openCmisSession.getRootFolder().getId());
                                    path.append("/root/");
                                    path.append(folder.getName());
                                    path.append("/");
                                    path.append(fileMove.getName());
                                    item.setUrl(path.toString());

                                    /**
                                     * CC. Nuevo Flujo Aprobaciones. Seteo de campo para tipo de documento
                                     * */
                                    item.setCodigoTipoDocumento( item.getCodigoTipoDocumento() );

                                    newFiles.add(item);
                                    logger.error("ARCHIVO MOVIDO !!!: ");
                                    Map<String, Object> properties = new HashMap<>();
                                    properties.put(PropertyIds.DESCRIPTION, "REGISTRADO");
                                    fileMove.updateProperties(properties, true);
                                }
                            }));
            return newFiles;
        } catch (Exception ex) {
            logger.error("Error al actualizar la version de los archivos", ex);
            throw new ServiceException("Error al actualizar la version de los archivos");
        }

    }


    @Override
    public void copyFile(String archivoId, String objectid, String destination) {
        try {
            logger.debug("Actualización de version del archivo");
            InitialContext ctx = new InitialContext();
            EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);
            Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);
            if (openCmisSession.exists(archivoId)) {

                Document doc = (Document) openCmisSession.getObject(archivoId);
                InputStream inputStream = doc.getContentStream().getStream();
                File file = new File(destination);

                logger.info("------> " + file.getAbsolutePath());
                logger.info("------> " + file.getCanonicalPath());

                FileUtils.copyInputStreamToFile(inputStream, file);
                doc.delete();//Delete file
                /// openCmisSession.getObject(objectid).delete();

            }
        } catch (NamingException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteFiles(List<String> archivoIds) {
        try {
            logger.debug("Actualización de version del archivo");
            InitialContext ctx = new InitialContext();
            EcmService ecmSvc = (EcmService) ctx.lookup(LOOKUP_NAME);

            Session openCmisSession = ecmSvc.connect(repositoryName, repositoryKey);
            Optional.ofNullable(archivoIds)
                    .ifPresent(l -> l.stream()
                            .peek(id -> logger.debug("Actualizando el archivo id " + id))
                            .filter(openCmisSession::exists)
                            .forEach(id -> {
                                Document doc = (Document) openCmisSession.getObject(id);
                                doc.delete();
                            }));

        } catch (Exception ex) {
            logger.error("Error al elilminar los archivos", ex);
            throw new ServiceException("Error al elilminar los archivos");
        }
    }
}
