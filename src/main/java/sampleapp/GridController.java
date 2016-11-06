package sampleapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static sampleapp.FileRestHelper.toId;
import static sampleapp.FileRestHelper.toPath;

@RestController
@RequestMapping("/grid")
public class GridController {

    private static Map<Long, FileListJSON> filesListCache = new HashMap<>();
    private long idCounter = 0;

    public List<FileListJSON> getFileListDefault() {
        return getFileList("c_58__92_Dojo Workshop", null);
    }

//    @RequestMapping({"/filelist/{path}"})
//    public List<FileListJSON> getFileListSorted(@PathVariable(value = "path") String path, @PathVariable(value = "sort") String sort) {
//        System.out.println(sort);
//        List<FileListJSON> result = getFileListDefault();
//        return result;
//    }
    @RequestMapping({"/filelist/{path}"})
    public List<FileListJSON> getFileList(@PathVariable(value = "path") String path, @RequestParam(value = "sort", required = false) String sort) {
//        System.out.println(sort);
        if (path.equals("new")) {
            return new ArrayList();
        }
        File folder = new File(toPath(path));
        try {
            final List filesAndSubfiles = getFilesAndSubfiles(new ArrayList(), folder);
            if (sort != null) {
                Collections.sort(filesAndSubfiles, new FileListJSONComperator(sort));
            }
            return filesAndSubfiles;
        } catch (IOException ex) {
            Logger.getLogger(GridController.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     * @param path
     * @param id
     * @param sort
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/filelist/{path}/{id}", method = RequestMethod.GET)
    public FileListJSON getFileDetail(@PathVariable(value = "path") String path, @PathVariable(value = "id") long id, @RequestParam(value = "sort", required = false) String sort) throws IOException {
            System.out.println("sampleapp.GridController.getFileDetail() id:" + id);
            System.out.println("sampleapp.GridController.getFileDetail() name:" + filesListCache.get(id).getName());
            System.out.println("sampleapp.GridController.getFileDetail() path:" + filesListCache.get(id).getPath());
        return filesListCache.get(id);
    }

    @RequestMapping(path = "/filelist/{path}/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateFileDetail(@PathVariable(value = "path") String path, @PathVariable(value = "id") long id, @RequestBody FileListJSON input) {
        FileListJSON fileListJSONCache = filesListCache.get(id);
        if (!fileListJSONCache.getName().equals(input.getName())) {
            File document;
            document = new File(toPath(fileListJSONCache.getPath()));
            File newfile = new File(document.getParentFile().getPath() + "/" + input.getName() + input.getType());
            document.renameTo(newfile);
            input.setPath(toId(newfile.getPath()));
            fileListJSONCache.setName(input.getName());
            fileListJSONCache.setPath(toId(newfile.getPath()));
            System.out.println("sampleapp.GridController.updateFileDetail() id:" + id);
            System.out.println("sampleapp.GridController.updateFileDetail() name:" + filesListCache.get(id).getName());
            System.out.println("sampleapp.GridController.updateFileDetail() path:" + filesListCache.get(id).getPath());
//            input.setId(toId(newfile.getPath()));
//            filesListCache.put(input.getId(), input);
//            filesListCache.remove(fileListJSONCache.getId());
        }
        return new ResponseEntity<>(input, HttpStatus.CREATED);
    }

    private List<FileListJSON> getFilesAndSubfiles(List<FileListJSON> result, File folder) throws IOException {
        filesListCache.clear();
        idCounter = 0;
        if (!folder.isDirectory()) {
            return new ArrayList();
        }
        File[] listFiles = folder.listFiles();
        for (File listFile : listFiles) {
            if (listFile.isDirectory()) {
                getFilesAndSubfiles(result, listFile);
            } else {
                result.add(createFileListJSON(listFile));
            }

        }
        return result;
    }

    private FileListJSON createFileListJSON(File listFile) throws IOException {
        FileListJSON fileListJSON;
        String name = listFile.getName();
        String extention = "";
        if (name.contains(".")) {
            name = listFile.getName().substring(0, listFile.getName().lastIndexOf("."));
            extention = listFile.getName().substring(listFile.getName().lastIndexOf("."));
        }
        fileListJSON = new FileListJSON(idCounter, FileRestHelper.toId(listFile.getAbsolutePath()), name,
                extention,
                (long) Files.getAttribute(listFile.toPath(), "size"),
                new Date(((FileTime) Files.getAttribute(listFile.toPath(), "creationTime")).toMillis()),
                new Date(listFile.lastModified()));
        filesListCache.put(idCounter++, fileListJSON);
        return fileListJSON;
    }

    private class FileListJSONComperator implements Comparator<FileListJSON> {

        private final String sort;

        public FileListJSONComperator(String sort) {
            this.sort = sort;

        }

        @Override
        public int compare(FileListJSON o1, FileListJSON o2) {
            if (sort.equals("(asc_creationDate)")) {
                return o2.getCreationDate().compareTo(o1.getCreationDate());
            } else if (sort.equals("(desc_creationDate)")) {
                return o1.getCreationDate().compareTo(o2.getCreationDate());
            } else if (sort.equals("(asc_modificationDate)")) {
                return o2.getModificationDate().compareTo(o1.getModificationDate());
            } else if (sort.equals("(desc_modificationDate)")) {
                return o1.getModificationDate().compareTo(o2.getModificationDate());
            } else if (sort.equals("(asc_name)")) {
                return o2.getName().compareTo(o1.getName());
            } else if (sort.equals("(desc_name)")) {
                return o1.getName().compareTo(o2.getName());
            } else if (sort.equals("(asc_type)")) {
                return o2.getType().compareTo(o1.getType());
            } else if (sort.equals("(desc_type)")) {
                return o1.getType().compareTo(o2.getType());
            } else if (sort.equals("(asc_size)")) {
                return ((Long) o2.getSize()).compareTo(((Long) o1.getSize()));
            } else if (sort.equals("(desc_size)")) {
                return ((Long) o1.getSize()).compareTo(((Long) o2.getSize()));
            } else {
                return 0;
            }
        }

    }

}
