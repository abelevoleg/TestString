public class Main {
    public static void main(String[] args) {
        String inputString = "ab11[xyz]4[xy]z2[3[x]y]ab";
        String outputString;

        if (CheckingString.isValidString(inputString)) {
            outputString = UnpackingString.unpack(inputString);
            System.out.println(outputString);
        } else System.out.println("Строка не прошла валидацию");
    }
}
