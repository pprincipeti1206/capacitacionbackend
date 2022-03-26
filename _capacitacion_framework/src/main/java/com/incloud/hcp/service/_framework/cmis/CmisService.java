package com.incloud.hcp.service._framework.cmis;


import com.incloud.hcp.cmis.CmisFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrador on 26/09/2017.
 */
public interface CmisService {

    CmisFile createDocumento(String folderId, MultipartFile file);

    CmisFile createOrReplaceDocumento(String folderId, MultipartFile file) throws Exception;

    String createFolder(String folder);

    boolean folderExist(String folderId);

    void deleteFile(String archivoId);

    void deleteFiles(List<String> archivoIds);

    List<CmisFile> getListFileByFolderId(String folderId);

    void updateFileLastVersionTrue(List<String> archivoIds);

    public void copyFile(String archivoId, String objectid, String destination);

    List<CmisFile> updateFileAndMove(List<CmisFile> archivos, String targetFolderId);

    List<CmisFile> updateFileAndMoveVerificar(List<CmisFile> files, String folderId);

    public CmisFile createOrReplaceDocumentoStream(
            String folderId,
            String nameFile,
            Long sizeFile,
            String typeFile,
            InputStream stream) throws Exception;
}
