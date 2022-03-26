package com.incloud.hcp.service._framework.cmis.impl;

import com.incloud.hcp.cmis.CmisFile;
import com.incloud.hcp.exception.ServiceException;
import com.incloud.hcp.service._framework.cmis.CmisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrador on 26/09/2017.
 */

@Profile("devlocal")
@Component("cmisService")
public class CmisServiceDevLocalImpl implements CmisService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public CmisFile createDocumento(String folderId, MultipartFile file) {
        return null;
    }

    @Override
    public String createFolder(String nameFolder) throws ServiceException {
        return null;
    }

    @Override
    public void deleteFile(String archivoId) {
        return;
    }

    @Override
    public boolean folderExist(String folderId) {
        return true;
    }

    @Override
    public List<CmisFile> getListFileByFolderId(String folderId) {
        return null;
    }

    @Override
    public void updateFileLastVersionTrue(List<String> filesId) {
        return;
    }

    @Override
    public List<CmisFile> updateFileAndMove(List<CmisFile> files, String folderId) {
        return null;

    }
    @Override
    public void copyFile(String archivoId,String objectid,String destination) {
        return;
    }

    @Override
    public void deleteFiles(List<String> archivoIds) {
        return;
    }

    public CmisFile createOrReplaceDocumento(String folderId, MultipartFile file) throws Exception {
        return null;
    }

    public List<CmisFile> updateFileAndMoveVerificar(List<CmisFile> files, String folderId) {
        return null;
    }

    public CmisFile createOrReplaceDocumentoStream(
            String folderId,
            String nameFile,
            Long sizeFile,
            String typeFile,
            InputStream stream) throws Exception {
        return null;
    }
}
