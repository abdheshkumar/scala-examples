import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class PhotoArrangement {
    public static void main(String[] args) {

        String input = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
                "john.png, London, 2015-06-20 15:13:22\n" +
                "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
                "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
                "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
                "BOB.jpg, London, 2015-08-05 00:02:03\n" +
                "notredame.png, Paris, 2015-09-01 12:00:00\n" +
                "me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
                "a.png, Warsaw, 2016-02-13 13:33:50\n" +
                "b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
                "c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
                "d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
                "e.png, Warsaw, 2016-01-02 09:49:09\n" +
                "f.png, Warsaw, 2016-01-02 10:55:32\n" +
                "g.jpg, Warsaw, 2016-02-29 22:13:11";

        long s = System.currentTimeMillis();
        System.out.println(solution(input));
        long e = System.currentTimeMillis();
        System.out.println(e - s);

    }

    public static String solution(String S) {
        // write your code in Java SE 8

        List<Image> images = new ArrayList<>();
        Set<String> cities = new LinkedHashSet<>();

        String[] lines = S.split("[\\r\\n]+");
        for (int i = 0; i < lines.length; i++) {
            Image image = null;
            String[] line = lines[i].split(","); //Parse line
            for (int j = 0; j < line.length; j++) {
                String[] photoWithExt = line[0].split("\\.");
                image = new Image(i, photoWithExt[0], line[1], getDate(line[2]), photoWithExt[1]);
                cities.add(line[1]);

            }
            images.add(image);
        }

        List<Image> result = new ArrayList<>();

        for (String city : cities) {
            List<Image> imagesByCity = images.stream()
                    .filter(p -> p.getCity().equalsIgnoreCase(city))
                    .collect(Collectors.toList());

            Collections.sort(imagesByCity, Comparator.comparing(Image::getDate));
            int size = imagesByCity.size();
            int largestInTheGroup = (int) (Math.log10(size) + 1);
            for (int i = 0; i < size; i++) {
                String format = String.format("%0" + largestInTheGroup + "d", i + 1);
                Image image = imagesByCity.get(i);
                image.setSeq(format);
                result.add(image);
            }
        }


        Collections.sort(result, Comparator.comparingInt(Image::getId));

        StringBuilder stringBuilder = new StringBuilder();
        for (Image img : result) {
            stringBuilder.append(img.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static Date getDate(String args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static class Image {


        public Image(int id, String name, String city, Date date, String extension) {
            this.id = id;
            this.name = name;
            this.city = city;
            this.date = date;
            this.extension = extension;
        }

        @Override
        public String toString() {
            return city.trim() + seq + "." + extension;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getSeq() {
            return seq;
        }

        public void setSeq(String seq) {
            this.seq = seq;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        int id;
        String name;
        String city;
        Date date;
        String seq;
        String extension;

    }
}
