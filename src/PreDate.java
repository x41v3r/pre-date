import java.util.Scanner;

public class PreDate {
    public static void main(String[] args) {
        System.out.print("请以“YYYY-MM-DD”格式输入年份（不可有多余空格）：");
        Scanner scanner = new Scanner(System.in);
        String dateString = scanner.nextLine();

        if(dateString.length() != 10){
            System.out.println("输入格式错误（长度不符）");
            return;
        }//健壮性代码：判定长度

        int year,month,day;
        int i = 0;
        String tmp = "";
        while (i <= 3){
            tmp += dateString.charAt(i);
            i += 1;
        }
        if(! isAllNum(tmp)){
            System.out.println("输入格式错误（年份中含有非数字）");
            return;
        }//健壮性代码：判断年份中是否含有字母
        year = Integer.valueOf(tmp);
        if(dateString.charAt(i) != '-'){
            System.out.println("输入格式错误（分隔符不为'-'）");
            return;
        }//健壮性代码：判断分隔符是否为'-'
        i += 1;//跳过中间的间隔'-'
        tmp = "";
        while (i <= 6){
            tmp += dateString.charAt(i);
            i += 1;
        }
        if(! isAllNum(tmp)){
            System.out.println("输入格式错误（月份中含有非数字）");
            return;
        }//健壮性代码：判断月份中是否含有字母
        month = Integer.valueOf(tmp);
        if(dateString.charAt(i) != '-'){
            System.out.println("输入格式错误（分隔符不为'-'）");
            return;
        }//健壮性代码：判断分隔符是否为'-'
        i += 1;//跳过中间的间隔'-'
        tmp = "";
        while (i <= 9){
            tmp += dateString.charAt(i);
            i += 1;
        }
        if(! isAllNum(tmp)){
            System.out.println("输入格式错误（日期中含有非数字）");
            return;
        }//健壮性代码：判断日期中是否含有字母
        day =  Integer.valueOf(tmp);

        int[] dataArray = new int [3];
        dataArray[0] = year;
        dataArray[1] = month;
        dataArray[2] = day;
        preDate(dataArray);

    }

    private static boolean preDate(int[] dateArray){
        int year = dateArray[0];
        int month = dateArray[1];
        int day = dateArray[2];
        int[] months = new int[13];//mounth数组，存储每个月的天数
        // 第一个元素为1表示为闰年
        //第一个元素为0表示为平年

        if(year % 4 == 0){
            //闰年
            months[0] = 1;//带一个元素置为1，表示这是闰年的情况
            months[1] = months[3] = months[5] = months[7] = months[8] = months[10] = months[12] = 31;
            months[4] = months[6] = months[9] = months[11] = 30;
            months[2] = 29;

            if(year < 1822 || year > 2022){
                System.out.println("数值范围错误（年份范围不合法）");
                return false;
            }

            if(month < 1 || month > 12){
                System.out.println("数值范围错误（月份范围不合法）");
                return false;
            }

            if(day <1 || day > months[month]){
                System.out.println("数值范围错误（日期范围不合法）");
                return false;
            }

            if(day > 1){
                day -= 1;
                formatPrint(year,month,day);
                return true;
            }else if(month > 1){
                month -= 1;
                day = months[month];
                formatPrint(year,month,day);
                return true;
            }else {
                year -= 1;
                month = 12;
                day = months[month];
                formatPrint(year,month,day);
                return true;
            }

        }else{
            //平年
            months[0] = 0;//带一个元素置为1，表示这是闰年的情况
            months[1] = months[3] = months[5] = months[7] = months[8] = months[10] = months[12] = 31;
            months[4] = months[6] = months[9] = months[11] = 30;
            months[2] = 28;

            if(year < 1822 || year > 2022){
                System.out.println("数值范围错误（年份范围不合法）");
                return false;
            }

            if(month < 1 || month > 12){
                System.out.println("数值范围错误（月份范围不合法）");
                return false;
            }

            if(day <1 || day > months[month]){
                System.out.println("数值范围错误（日期范围不合法）");
                return false;
            }

            if(day > 1){
                day -= 1;
                formatPrint(year,month,day);
                return true;
            }else if(month > 1){
                month -= 1;
                day = months[month];
                formatPrint(year,month,day);
//                System.out.println("" + year + '-' + month + '-' + day);
                return true;
            }else {
                year -= 1;
                month = 12;
                day = months[month];
                formatPrint(year,month,day);
//                System.out.println("" + year + '-' + month + '-' + day);
                return true;
            }
        }

//        return true;
    }

    private static boolean isAllNum(String tmp){
        boolean flag = true;

        int i = 0;
        while (i < tmp.length()){
            if(! Character.isDigit(tmp.charAt(i))){
                flag = false;
            }
            i += 1;
        }

        return flag;
    }//判断字符串是否全为数字

    private static void formatPrint(int year,int month,int day){
        String yearString = "" + year;

        String monthString = "";
        if(month < 10){
            monthString = "0" + month;
        }else {
            monthString = "" + month;
        }

        String dayString = "";
        if(day < 10){
            dayString = "0" + day;
        }else {
            dayString = "" + day;
        }

        System.out.println(yearString + '-' + monthString + '-' + dayString);
    }//格式化输出
}