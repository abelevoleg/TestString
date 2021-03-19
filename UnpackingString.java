import java.util.ArrayList;
import java.util.List;

class UnpackingString {
    private static String unpacking;
    private static List<String> str = new ArrayList<>();

    static String unpack(String original) {
        unpacking = "";
        split(original);
        runUnpack();
        return unpacking;
    }

    // составление из полученного в методе split() списка требуемой (распакованной) строки
    private static void runUnpack(){
        for (int i = 0; i < str.size(); i++){
            // находим элемент, состоящий из букв
            if (str.get(i).matches("[a-zA-Z]+")) {
                // если этот элемент первый, прибавляем его к строке, удаляем его из списка и запускаем цикл заново
                if (i == 0){
                    unpacking = unpacking + str.get(i);
                    str.remove(i);
                    if (!str.isEmpty()) runUnpack();
                    break;
                    /* если слева от элемента знак открывающейся скобки, берем число, стоящее за скобкой,
                    записываем элемент нужное число раз на место числа, удаляем элемент и скобки */
                } else if (str.get(i - 1).equals("[")){
                    String s = "";
                    for (int j = 0; j < Integer.parseInt(str.get(i - 2)); j++){
                        s = s + str.get(i);
                    }
                    str.set(i - 2, s);
                    str.remove(i-1);
                    str.remove(i-1);
                    str.remove(i-1);
                    /* Далее проверяем, находится ли справа от записанного элемента другой элемент из букв. Если да,
                    то их нужно объединить */
                    if (str.size() > 1 && str.get(i - 1).matches("[a-zA-Z]+")){
                        s = str.get(i - 2) + str.get(i - 1);
                        str.set(i - 2, s);
                        str.remove(i - 1);
                    }
                    if (!str.isEmpty()) runUnpack(); /* если после удаления в списке еще остались элементы, запускаем
                                                         метод заново*/
                    break;
                } else if (str.get(i - 1).equals("]")){
                    /* если перед буквенным элементом закрывающаяся скобка, просто записываем его в выходную строку,
                    удаляем из списка и запускаем метод заново */
                    unpacking = unpacking + str.get(i);
                    str.remove(i);
                    if (!str.isEmpty()) runUnpack();
                    break;
                }
            }
        }
    }

    // разделение исходной строки на элементы типа а)число, б)набор букв, идущих друг за другом, в)скобка и заполнение этими элементами списка
    private static void split(String original){
        String originalSub = null;
        char[] chars = original.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if ((i + 1) > chars.length - 1){
                /* если элемент последний, добавляем его в список без проверок */
                str.add(original);
            } else if (!(Character.isLetter(chars[i]) == Character.isLetter(chars[i + 1])) ||
                    chars[i] == '[' || chars[i] == ']' ||
                    chars[i + 1] == '[' || chars[i + 1] == ']'){
                /* если следующий символ другого типа (не буква) или скобка, или если сам проверяемый символ - скобка,
                добавляем получившийся элемент в список, убираем из строки этот элемент, выходим из цикла и запускаем
                метод заново (рекурсия), пока в нашей строке остаются символы*/
                str.add(original.substring(0, i + 1));
                originalSub = original.substring(i + 1);
                break;
            }
        }
        if (originalSub != null) split(originalSub);
    }
}
