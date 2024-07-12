package org.heimtn;

public class Main {
    /**
     * Константы вынесены отдельно для удобства ручного тестирования
     */
    //Количество морковок на полянах (индекс + 1 = номер поля)
    public static final int[] countCarrotInArea = {5,3,3,5,2};
    //Вес 1 морковки на поле (индекс + 1 = номер поля)
    public static final int[] weightCarrotInArea = {1,2,3,4,5};
    //Количество ходок
    public static final int countMovesInDay = 10;
    //Количество дней
    public static final int countDays = 1;
    //Сколько может унести заяц веса за ходку
    public static final int maxWeightMoveRabbit = 5;

    public static void main(String[] args){
        //Переменная общего веса морковок которые вынесет заяц
        int allWeightCarrotCollected = 0;
        //Подсчет всех ходок за все дни
        int allCountMoves = countMovesInDay*countDays;
        //Обьединение кол-ва и веса марковок в двумерный массив для упрощения дальшейшей работы
        int[][] areaData = new int[2][];
        areaData[0] = countCarrotInArea.clone();
        areaData[1] = weightCarrotInArea.clone();
        //Сортировка массива от наибольшего к наименьшему по весу
        areaData = sortedIntMassive(areaData);

        //Цикл ходок, каждый проход цикла это одна ходка
        for(int i = 1; i <= allCountMoves; i++){
            //Локальная переменная с максимальным переносимым весом
            int maxWeight = maxWeightMoveRabbit;
            //Переменная с общим весом за ходку
            int weightInMove = 0;

            /**
             * Основной алгоритм расчета необходимого кол-ва морковок которые нужно взять зайцу.
             * Обязательное условие в том, чтобы массив был отсортирован по убыванию.
             * Первый цикл отвечает за проход по каждому полю.
             * Второй цикл отвечает за условие сбора. Пока максимальный вес больше или равен весу одной морковки
             * на данном поле и то что эта марковка там есть, то происходит "сбор морковки".
             * При "сборе морковки" во первых вычитается вес морковки с текущего поля из максимально переносимого веса,
             * далее к переменной с общим весом прибавляется вес морковки с текущего поля, далее идет вычитаение морковки
             * из кол-ва данной морковки на поле.
             * Далее для простенького логирования в консоль выводится сообщение с номером захода и весами.
             */
            for(int j = 0; j < areaData[1].length; j++){
                while(maxWeight >= areaData[1][j] && areaData[0][j] > 0){
                    maxWeight -= areaData[1][j];
                    weightInMove += areaData[1][j];
                    areaData[0][j]--;
                    System.out.println("заход: "+i+", взял вес: "+ areaData[1][j] + ", общий вес за заход: "+weightInMove);
                }
            }
            //После захода прибавляем вынесенный вес морковок к общему количеству
            allWeightCarrotCollected += weightInMove;
        }
        //Вывод результата
        System.out.println(allWeightCarrotCollected);
    }
    //Пузырьковая сортировка двумерного массива
    public static int[][] sortedIntMassive(int[][] mas){
        int lengthMass = mas[0].length;
        int[][] massive = mas.clone();
        for(int i = 0; i < lengthMass-1;i++){
            for(int j = 0; j < lengthMass-i-1;j++){
                if (massive[1][j] < massive[1][j+1]) {
                    for(int g = 0; g < mas.length; g++) {
                        int temp = massive[g][j];
                        massive[g][j] = massive[g][j + 1];
                        massive[g][j + 1] = temp;
                    }
                }
            }
        }
        return massive;
    }
}
