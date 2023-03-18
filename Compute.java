import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ndzib (Serkwi bruno)
 */
public class Compute {
    public static void main(String[] args) {
        String[] students = new String[]{"Serkwi Bruno;1;1;1", "Bruno Ndzi:  5:  10 :10"};

        Compute c = new Compute();
        System.out.println(c.compute(students));
    }

    public String compute(String[] students) {
        StringBuilder sb = new StringBuilder("");

        for(String student: students) {
            StringBuilder nameBuilder = new StringBuilder("");
            int totalMarks = 0;
            int marksCount = 0;

            // find the separator by looking for the character before the first number
            Pattern pattern = Pattern.compile("[0-9]");
            Matcher matcher = pattern.matcher(student);
            matcher.find();

            int separatorIndex = matcher.start()-1;
            String separator = student.substring(separatorIndex, separatorIndex+1);

            //ensure that blanks are not mistaken for separators
            if(separator.equals(" ")) {
                for(int i=separatorIndex; i>0; i--) {
                    if(!student.substring(i, i+1).equals(" ")) {
                        separatorIndex = i;
                        separator = student.substring(separatorIndex, separatorIndex+1);
                        break;
                    }
                }
            }

            // split the student data and get the name
            String[] studentInfos = student.split(separator);
            nameBuilder.append(studentInfos[0].strip());

            // after removing the name, sum all marks less than or equal to 10
            for(int i=1; i<studentInfos.length; i++) {
                int mark = Integer.parseInt(studentInfos[i].strip());

                if (mark <= 10) {
                    totalMarks += mark;
                    marksCount++;
                }
            }

            // compute the average of the student's marks
            int studentAverage = totalMarks/Math.max(1, marksCount);

            // add student's summary
            sb.append(String.format("%s-%d;", nameBuilder.toString(), studentAverage));
        }

        return sb.toString();
    }
}
