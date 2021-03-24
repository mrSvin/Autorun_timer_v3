import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    static int chas;
    static int min;
    static int day;
    static int triger=0;

    public static void main(String[] args)  {

        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        int chas_zad=23;
                        int min_zad=45;
                        int chas_zad_month=4;
                        int min_zad_month=10;

                        long unixTime = System.currentTimeMillis() / 1000L; //Определяем текущее время
                        Date date = new java.util.Date(unixTime*1000L);

                        SimpleDateFormat dd = new java.text.SimpleDateFormat("dd");
                        SimpleDateFormat HH = new java.text.SimpleDateFormat("HH");
                        SimpleDateFormat mm = new java.text.SimpleDateFormat("mm");
                        SimpleDateFormat ss = new java.text.SimpleDateFormat("ss");
                        String day_string = dd.format(date);
                        String hour = HH.format(date);
                        String min_string = mm.format(date);
                        String sec = ss.format(date);

                        chas=Integer.parseInt(hour);
                        min=Integer.parseInt(min_string);
                        day=Integer.parseInt(day_string);
                        System.out.println(day + " число " + hour +":" +  min +":"+ sec + " Условие срабатывания суточного отчета: " +chas_zad+":"+min_zad
                                + " Условие срабатывания месячного отчета(1 числа): " +chas_zad_month+":"+min_zad_month);
                        //Условие для запуска суточного отчета ботом
                        if (chas==chas_zad && min==min_zad && triger!=1) {
                            System.out.println("Условие для суточного отчета выполнилось!");
                            Runtime.getRuntime().exec("cmd /c C:\\Users\\ВасильевАВ\\AppData\\Local\\Clickermann/bot.bat");
                            triger=1;
                        }

                        //Условие для запуска ежемесячного отчета ботом
                        if (day==1 && chas==chas_zad_month && min==min_zad_month && triger!=2) {
                            System.out.println("Условие для месячного отчета выполнилось!");
                            Runtime.getRuntime().exec("cmd /c C:\\Users\\ВасильевАВ\\AppData\\Local\\Clickermann/bot_month.bat");
                            triger=2;
                        }

                        //Обнуляем тригер для возможности повторного запуска
                        if (min!=min_zad && min!=min_zad_month) {
                            triger=0;
                        }

                        Thread.sleep(45000); //1000 - 1 сек (45 сек)
                    } catch (InterruptedException | IOException ex) {
                    }
                }
            }
        });
        run.start(); // заводим
    }


}