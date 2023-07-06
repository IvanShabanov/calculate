public class stringttt  {


public static void main(String[] args) {
    /*строка с примером*/
    String primer = "(1/(2))*((1)/(-4))"; 
    /*вызываем метод для подсчета примера в троке*/ 
    calculate ( primer );
}
/*статический метод который возвращает строку с результатом подсчета*/
static String calculate (String primer) {
    /*переменная....*/
    float rezult = 0;
    /*переменная для хранения позици искомых символов*/
    int position = 0;
    int position2 = 0;
    /*позиция старта (начало примера итп) */
    int posstart = 0;
    /*позиция конца примера и тп */
    int posend = 0;
    String simvol;
    String numbers = "1234567890.-"; 

    String znaki  = "*/+-";
    String znaki2 = "/*-+";
    int curZnak = 0;
    String strCurZnak = "";
    /*переменная в которой хранится строка с результатом*/
    String strRezult;


    /*вывод на экран примера который к нам пришел из параметра метода*/
    System.out.println(primer);
    /*поиск позиции символа ( в примере, переменная position будет хранить позицию этого символа*/ 
    position = primer.indexOf("(");
    /* пока позиция символа ( больше либо равно 0, то будем выполнять цикл в котором ищем все скобки */
    while (position >= 0) {
        /* переменна для подсчета количества открытых скобок и т.к. мы выше нашли одну то количество прирявняли к единицы*/
        int skobok = 1;
        /* Длина чего то.....*/
        int length = 0;
        /*приравниваем переменную posstsrt к позиции первой ( */
        posstart = position;
        /*пока текущая позиция + длина меньше длинны примера и количество скобок больше 0 будет выполняться цикл для поиска позиции закрывающей скобки */
        while ((position + length < primer.length() ) && (skobok > 0)) {
            /* увеличение длинны на 1 */
            length ++;
            /*сохраним в переменной simvol символ на позиции такой-то, 
              Character.toString - преобразование из переменной char в переменную string
             primer.charAt - получения символа из примера на позиции position + length */
            simvol = Character.toString ( primer.charAt(position + length) );
           
            /*условие сравниваем simvol с (, т.к. переменная simvol типа string то сравнивается методом equals /
             */
            if (simvol.equals ("(") ) {
                /*условие сработало - увеличим ко-во скобок на 1 */
                skobok = skobok + 1;
            }
            /*сравниваем символ с другой скобкой ) */
            if (simvol.equals (")") ) {
                /*условие сработало - уменьшаем на 1 */
                skobok = skobok - 1;
            }
            /*отладка*/
           /* System.out.println(simvol);
            System.out.println(skobok);
            System.out.println("-------------");*/
        }
        /*запишем в posend позицию закрывающей скобки в переменную posend */
        posend = position + length + 1;
        /*запишем в simvol подстроку из примера с posstsrt до posend со () */
        simvol = primer.substring( posstart, posend);
        /*запишем в strRexult подстроку с позициями внутри скобочек */
        strRezult = primer.substring( posstart + 1 , posend - 1);
        /*отправим на подсчет (в метод calculate) содержимое strRezult и запишем результат в эту же переменную  */
        strRezult = calculate(strRezult);
        /*заменем в primer значение в simvol (пример со скобками) на результат подсчета  и запишем в primer */
        primer = primer.replace(simvol, strRezult);
        /*ищем следующую скобку, и запишем в position */
        position = primer.indexOf("(");
        /*выведем текущий пример*/
        System.out.println(primer);
        /*конец цикла */
    }
    

/*запишем в position позицию знака умножить */

    position = primer.indexOf(znaki.substring(curZnak, curZnak + 1), 1);
    position2 = primer.indexOf(znaki2.substring(curZnak, curZnak + 1), 1);
    
    if ((position > 0) && (position2 > 0))  {
        position = Math.min(position, position2); 

    } else if (position2 > 0) {
        position = position2;
    }
  
    while ((position <= 0) && (curZnak < znaki.length() - 1 )) {
        curZnak = curZnak + 1;
        position = primer.indexOf(znaki.substring(curZnak, curZnak + 1), 1);
        position2 = primer.indexOf(znaki2.substring(curZnak, curZnak + 1), 1);
        if ((position > 0) && (position2 > 0))  {
            position = Math.min(position, position2); 
        } else if (position2 > 0) {
            position = position2;
        }
    }    
    while ( position > 0) {
        strCurZnak = primer.substring(position, position + 1);
        posstart = position - 1;
        simvol = Character.toString ( primer.charAt(posstart) );
       
        while ( ( numbers.contains(simvol)  ) && (posstart > 0) ) {
            posstart = posstart - 1;
            simvol =Character.toString ( primer.charAt(posstart) );
            if (simvol.equals("-")){
                break;
            }
        }
        if (! numbers.contains(simvol) ) {
            posstart = posstart + 1;
        }
        posend = position + 1;
        simvol =Character.toString ( primer.charAt(posend) );
        while ( (  numbers.contains(simvol) ) && (posend < primer.length() - 1) ) {
            posend = posend + 1;
            simvol =Character.toString ( primer.charAt(posend) );
            if (simvol.equals("-")){
                posend = posend - 1;
                break;
            }
        }
        if (numbers.contains(simvol) ) {
            posend = posend + 1;
        }
        simvol = primer.substring( posstart, posend );
        rezult = calculateMult(simvol, strCurZnak);
       
        strRezult = Float.toString(rezult);
        primer = primer.replace(simvol, strRezult);
        
        position = primer.indexOf(znaki.substring(curZnak, curZnak + 1), 1);
        position2 = primer.indexOf(znaki2.substring(curZnak, curZnak + 1), 1);
       
        if ((position > 0) && (position2 > 0))  {
            position = Math.min(position, position2); 
        } else if (position2 > 0) {
            position = position2;
        }
        while ((position <= 0) && (curZnak < znaki.length() - 1 )) {
            curZnak = curZnak + 1;
            position = primer.indexOf(znaki.substring(curZnak, curZnak + 1), 1);
            position2 = primer.indexOf(znaki2.substring(curZnak, curZnak + 1), 1);
            if ((position > 0) && (position2 > 0))  {
                position = Math.min(position, position2); 
            } else if (position2 > 0) {
                position = position2;
            }
        }
        System.out.println(primer);
    }
    /*
    position = primer.indexOf("/");
    while ( position > 0) {
        posstart = position - 1;
        simvol = Character.toString ( primer.charAt(posstart) );
        while ( ( numbers.contains(simvol)  ) && (posstart > 0) ) {
            posstart = posstart - 1;
            simvol =Character.toString ( primer.charAt(posstart) );
            if (simvol.equals("-")){
                break;
            }
        }
        if (! numbers.contains(simvol) ) {
            posstart = posstart + 1;
        }
        posend = position + 1;
        simvol =Character.toString ( primer.charAt(posend) );
        while ( (  numbers.contains(simvol) ) && (posend < primer.length() - 1) ) {
            posend = posend + 1;
            simvol =Character.toString ( primer.charAt(posend) );
            if (simvol.equals("-")){
                posend = posend - 1;
                break;
            }
        }
        if (numbers.contains(simvol) ) {
            posend = posend + 1;
        }
        simvol = primer.substring( posstart, posend );
        rezult = calculateDELIT(simvol);
        strRezult = Float.toString(rezult);
        primer = primer.replace(simvol, strRezult);
        position = primer.indexOf("/");

        System.out.println(primer);
    } 

    position = primer.indexOf("+");
    while ( position > 0) {
        posstart = position - 1;
        simvol = Character.toString ( primer.charAt(posstart) );
        while ( ( numbers.contains(simvol)  ) && (posstart > 0) ) {
            posstart = posstart - 1;
            simvol =Character.toString ( primer.charAt(posstart) );
            if (simvol.equals("-")){
                break;
            }
        }
        if (! numbers.contains(simvol) ) {
            posstart = posstart + 1;
        }
        posend = position + 1;
        simvol =Character.toString ( primer.charAt(posend) );
        while ( (  numbers.contains(simvol) ) && (posend < primer.length() - 1) ) {
            posend = posend + 1;
            simvol =Character.toString ( primer.charAt(posend) );
            if (simvol.equals("-")){
                posend = posend - 1;
                break;
            }
        }
        if (numbers.contains(simvol) ) {
            posend = posend + 1;
        }
        simvol = primer.substring( posstart, posend );
        rezult = calculatePLUS(simvol);
        strRezult = Float.toString(rezult);
        primer = primer.replace(simvol, strRezult);
        position = primer.indexOf("+");

        System.out.println(primer);
    } 
    position = primer.indexOf("-");
    while ( position > 0) {
        posstart = position - 1;
        simvol = Character.toString ( primer.charAt(posstart) );
        while ( ( numbers.contains(simvol)  ) && (posstart > 0) ) {
            posstart = posstart - 1;
            simvol =Character.toString ( primer.charAt(posstart) );
            if (simvol.equals("-")){
                break;
            }
        }
        if (! numbers.contains(simvol) ) {
            posstart = posstart + 1;
        }
        posend = position + 1;
        simvol =Character.toString ( primer.charAt(posend) );
        while ( (  numbers.contains(simvol) ) && (posend < primer.length() - 1) ) {
            posend = posend + 1;
            simvol =Character.toString ( primer.charAt(posend) );
            if (simvol.equals("-")){
                posend = posend - 1;
                break;
            }
        }
        if (numbers.contains(simvol) ) {
            posend = posend + 1;
        }
        simvol = primer.substring( posstart, posend );
        rezult = calculateMINUS(simvol);
        strRezult = Float.toString(rezult);
        primer = primer.replace(simvol, strRezult);
        position = primer.indexOf("-");

        System.out.println(primer);
    } 
    */
    return primer;

}
/*
static float calculatePLUS (String primer) {
    float rezult = 0; 
    float a = 0;
    float b = 0;
    int position = 0; 
    String strA, strB;
    position = primer.indexOf("+");
    strA = primer.substring(0, position); 
    strB = primer.substring(position + 1, primer.length());
    a = Float.parseFloat(strA);
    b = Float.parseFloat(strB);
    rezult = a + b;
   return rezult;

}
static float calculateMINUS (String primer) {
    float rezult = 0; 
    float a = 0;
    float b = 0;
    int position = 0; 
    String strA, strB;
    position = primer.indexOf("-", 1);
    strA = primer.substring(0, position); 
    strB = primer.substring(position + 1, primer.length());
    a = Float.parseFloat(strA);
    b = Float.parseFloat(strB);
    rezult = a - b;
   return rezult;

}*/
static float calculateMult (String primer, String znak) {
    float rezult = 0; 
    float a = 0;
    float b = 0;
    int position = 0; 
    String strA, strB;
    position = primer.indexOf(znak, 1);
    strA = primer.substring(0, position); 
    strB = primer.substring(position + 1, primer.length());
    a = Float.parseFloat(strA);
    b = Float.parseFloat(strB);
    if (znak.equals ("+")) {
        rezult = a + b;
    }
    if (znak.equals ("-")) {
        rezult = a - b;
    }
    if (znak.equals ("/")) {
        rezult = a / b;
    }
    if (znak.equals ("*")) {
        rezult = a * b;
    }
   return rezult;

} 
/*
static float calculateDELIT (String primer) {
    float rezult = 0; 
    float a = 0;
    float b = 0;
    int position = 0; 
    String strA, strB;
    position = primer.indexOf("/");
    strA = primer.substring(0, position); 
    strB = primer.substring(position + 1, primer.length());
    a = Float.parseFloat(strA);
    b = Float.parseFloat(strB);
    rezult = a / b;
   return rezult;

}
static float calculateUMNOG (String primer) {
    float rezult = 0; 
    float a = 0;
    float b = 0;
    int position = 0; 
    String strA, strB;
    position = primer.indexOf("*");
    strA = primer.substring(0, position); 
    strB = primer.substring(position + 1, primer.length());
    a = Float.parseFloat(strA);
    b = Float.parseFloat(strB);
    rezult = a * b;
   return rezult;

}*/
/*
static void Privetstvie (String name, String fam, String otch) {
   
    if (otch.endsWith("вна")) {
        System.out.println("Здравствуйте г-жа " + fam + " " +  name + " " + otch);   
    } else {
        System.out.println("Здравствуйте г-н " + fam + " " +  name + " " + otch);    
    }

} 

static int Chislobukv (String stroka, String bukva) {
    int rezult = 0;
    
    for (int i = 0; i < stroka.length(); i++) {
        
        String simvol = stroka.substring(i, i + 1);
        if (simvol.equals(bukva)) {
            rezult++;   
        }
        
    } 
     return rezult;
}*/

}