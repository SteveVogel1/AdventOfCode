package twentytwo.daySeven;

import twentytwo.helper.DailyTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaySeven implements DailyTask {

    public static void main(String[] args) {
        DaySeven day = new DaySeven();
        day.run();
    }

    public String getFileName() {
        return "src/twentytwo/daySeven/input.txt";
    }

    public String taskA(List<String> input){
        Directory directory = createDirectory(input);
        return String.valueOf(getSize(directory));
    }

    private Directory createDirectory(List<String> input) {
        Directory baseDir = new Directory("/", null);
        baseDir.parent = baseDir;
        Directory currentDir = baseDir;
        for(String line : input){
            if(line.startsWith("$")){
                //Commands
                if(line.startsWith("$ cd")){
                    //Change directory
                    line = line.replace("$ cd ", "");
                    if(line.equals("..")){
                        currentDir = currentDir.getParent();
                    } else if (line.equals("/")) {
                        currentDir = baseDir;
                    }else{
                        currentDir = currentDir.getDirectoryByName(line.trim());
                    }
                }
            }else{
                //listing
                if(line.startsWith("dir")){
                    line = line.replace("dir ", "");
                    currentDir.getDirectoryByName(line.trim());
                }else{
                    String[] info = line.split(" ");
                    currentDir.addFile(info[1], Long.parseLong(info[0]));
                }
            }
        }
        return baseDir;
    }

    private long getSize(Directory dir){
        if(dir.directory.isEmpty()){
            return 0;
        }else{
            long currentSize = dir.getSize();
            if(currentSize > 100_000){
                currentSize = 0;
            }
            return currentSize + dir.directory.stream().filter(e -> e instanceof Directory).map(e -> (Directory) e).mapToLong(this::getSize).sum();
        }
    }

    public String taskB(List<String> input){
        Directory directory = createDirectory(input);

        List<Directory> directories = new ArrayList<>();
        long totalSpace = 70_000_000L;
        long minSpace = 30_000_000L;
        long freeSpace = totalSpace - directory.getSize();
        long minRemovedSpace = minSpace - freeSpace;
        getDirectoryWithSpace(directories, directory.directory, Math.abs(minRemovedSpace));

        return String.valueOf(directories.stream().mapToLong(Directory::getSize).min().orElse(-1));
    }

    public void getDirectoryWithSpace(List<Directory> out, List<Element> directories, long minSpace){
        for(Element e : directories){
            if(e instanceof Directory && e.getSize() > minSpace){
                out.add((Directory) e);
                getDirectoryWithSpace(out, ((Directory) e).directory, minSpace);
            }
        }
    }


    private interface Element{
        long getSize();
        Directory getParent();
        String getName();
    }

    private class Directory implements Element{

        String name;
        Directory parent;
        List<Element> directory = new ArrayList<>();

        public Directory(String name, Directory parent){
            this.name = name;
            this.parent = parent;
        }
        @Override
        public long getSize() {
            return directory.stream().mapToLong(Element::getSize).sum();
        }

        @Override
        public Directory getParent() {
            return parent;
        }

        @Override
        public String getName() {
            return name;
        }

        public Directory getDirectoryByName(String name){
            Optional<Directory> optDir = directory.stream().filter(dir ->
                    dir.getName().equals(name) && dir instanceof Directory).map(e -> (Directory) e).findFirst();
            if(optDir.isEmpty()){
                Directory dir = new Directory(name, this);
                this.directory.add(dir);
                return dir;
            }else{
                return optDir.get();
            }
        }

        public void addFile(String name, long size){
            directory.add(new File(name, this, size));
        }
    }

    private class File implements Element{
        String name;
        Directory parent;
        long size;
        public File(String name, Directory parent, long size){
            this.name = name;
            this.parent = parent;
            this.size = size;
        }
        @Override
        public long getSize() {
            return size;
        }

        @Override
        public Directory getParent() {
            return parent;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
