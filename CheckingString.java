class CheckingString {

    static boolean isValidString(String string){
        return checkSymbol(string) && checkDigit(string) && checkBracket(string);
    }

    // проверка наличия в строке недопустимых символов
    static boolean checkSymbol(String string){
        return !string.matches(".*[^a-zA-Z0-9\\[\\]].*");
    }

    // проверка наличия после цифры другой цифры или скобки, наличия цифры перед скобкой, что в строке скобка не стоит первой, а цифра - последней
    static boolean checkDigit(String string){
        return !string.matches(".*[0-9][^0-9\\[].*") && !string.matches(".*[^0-9]\\[.*")
                && !string.matches("\\[.*") && !string.matches(".*[0-9]");
    }

    // проверка количества и очередности скобок
    static boolean checkBracket(String string){
        int bracketCount = 0;
        for (char c : string.toCharArray()) {
            if (c == '[')
                bracketCount++;
            else if (c == ']')
                bracketCount--;
            if (bracketCount < 0) {
                return false;
            }
        }
        return bracketCount == 0;
    }

}
