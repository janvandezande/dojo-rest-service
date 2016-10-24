package sampleapp;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static sampleapp.FileRestHelper.toId;
import static sampleapp.FileRestHelper.toPath;

@RestController
@RequestMapping("/tree")
public class TreeController {

    @RequestMapping(value = "/")
    public FileJSON[] getFolder(@RequestParam(value = "id", defaultValue = "d_") String id) {
        return getTreeNode(id);
    }

    @RequestMapping(value = "/{id}")
    public ChildrenJSON getChildren(@PathVariable(value = "id") String name) {
        return getFileChildren(name);
    }

    private FileJSON[] getTreeNode(String name) {
        File file = new File(toPath(name));
        FileJSON[] result = new FileJSON[1];
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            result[0] = new FileJSON(toId(file.getPath()), true, file.getName(), files.length > 0);
        } else {
            result[0] = new FileJSON(toId(file.getPath()), false, file.getName(), false);
        }

        return result;
    }

    @RequestMapping("/file")
    public FilePropertiesJSON getProperties(@RequestParam(value = "filePath") String id) {
        File file = new File(toPath(id));

        try {
            FilePropertiesJSON filePropertiesJSON = new FilePropertiesJSON(
                    file.getName(),
                    Files.probeContentType(file.toPath()),
                    toPath(id),
                    new BigDecimal((Long) Files.getAttribute(file.toPath(), "size")),
                    new Date(((FileTime)Files.getAttribute(file.toPath(), "creationTime")).toMillis()), 
                    new Date(file.lastModified()), 
                    !file.canWrite(), 
                    file.isHidden(), 
                    new Date(), 
                    "", 
                    "", 
                    "", 
                    BigDecimal.ONE);
        return filePropertiesJSON;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ChildrenJSON getFileChildren(String name) {
        File file = new File(toPath(name));
        ChildrenJSON result;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            List<FileJSON> fileJSONs = new ArrayList<>();
            for (File childFile : files) {
                FileJSON fileJSON;
                if (childFile.isDirectory()) {
                    fileJSON = new FileJSON(toId(childFile.getPath()), childFile.isDirectory(), childFile.getName(), childFile.listFiles().length > 0);
                } else {
                    fileJSON = new FileJSON(toId(childFile.getPath()), childFile.isDirectory(), childFile.getName(), false);
                }
                fileJSONs.add(fileJSON);
            }
            result = new ChildrenJSON(toId(file.getPath()), true, file.getName(), fileJSONs.toArray(new FileJSON[files.length]));
        } else {
            result = new ChildrenJSON(toId(file.getPath()), false, file.getName(), new FileJSON[0]);
        }

        return result;
    }

}
