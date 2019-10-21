package TwoArraysForTesting;

public class Arrays {
    public static void main(String[] args) {
// Проверка метода afterFour
        int[] arr = {2, 7, 6, 8, 4, 3};
        int [] arr2 = afterFour(arr);

        System.out.println();
        for (int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
// Проверка метода onlyOneAndFour
        int[] array = {4, 4, 4, 4};
        System.out.println(onlyOneAndFour(array));

        for (int i : arr2){
            System.out.print(i + "  ");
        }
    }
// ================================
    static int[] afterFour(int[] intArray) throws RuntimeException{
        int[] newIntArray = null;  // переменная для хранения ссылки на новый массив
        int indexLastFour = 0;  //переменная для хранения индекса элемента массива со значением 4

        for (int i = 0; i < intArray.length; i++){
            if (intArray[i] == 4){
                indexLastFour = i;  // сохранили индекс элемента со значением 4
            }
        }
        if (indexLastFour > 0){  // если данный массив содержал число 4, то создаем новый
            newIntArray = new int[(intArray.length-1)-indexLastFour];
        }
        if(newIntArray != null){ // если новый массив создан, то копируем в него элементы идущие после последней 4-ки
            System.arraycopy(intArray, (indexLastFour + 1), newIntArray, 0, newIntArray.length);

            //если все удачно, то возвращаем новый массив, иначе пробрасываем RuntimeException()
            return newIntArray;
        } else throw new RuntimeException();
    }
// =======================================================
    static boolean onlyOneAndFour(int[] intArray){
        boolean b = true;
        int one = 0; // количество 1 или 4 (должно быть меньше размера массива)
        int four = 0;
        for (int n : intArray){
            switch (n){
                case 1: one++; break;
                case 4: four++; break;
                default: b = false;
            }
        }
        return b &&((one > 0 && one < intArray.length) && (four > 0 && four < intArray.length));
    }
}
