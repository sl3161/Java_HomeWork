package HW.HW6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

// Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
// Создать множество ноутбуков.
// Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
// Например:
// “Введите цифру, соответствующую необходимому критерию:
// 1 - ОЗУ
// 2 - Объем ЖД
// 3 - Операционная система
// 4 - Цвет …
// Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
// Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
// Работу сдать как обычно ссылкой на гит репозиторий
// Частые ошибки:
// 1. Заставляете пользователя вводить все существующие критерии фильтрации
// 2. Невозможно использовать более одного критерия фильтрации одновременно
// 3. При выборке выводятся ноутбуки, которые удовлетворяют только одному фильтру, а не всем введенным пользователем
// 4. Работа выполнена только для каких то конкретных ноутбуков, и если поменять характеристики ноутбуков или добавить еще ноутбук, то программа начинает работать некорректно

public class Program {
   
    public static void main(String[] args) {
    
            while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите необходимую цыфру меню или q для выхода:"+"\n"+" 1 - Добавление записи "+"\n"+" 2 - Вывод списка "); 
            System.out.println(" 3 - Поиск");       
                switch (scanner.nextLine()) {
                    case "1":
                        AddNotebook();
                        break;
                        case "q":
                        return;
                        case "2":
                        for (Notebooks item : GetArrayList()) {
                            System.out.println(item);
                        }
                        break;
                        case "3":
                        Find();
                        break;
                        default:
                        System.out.println("Вы ввели неверный символ для выбора , введите заново");
                        break;
                    }
                
                }  
        }         
    static void AddNotebook (){
        ArrayList<Notebooks> notes = new ArrayList<>();
        Notebooks note1 = new Notebooks();
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Введите название ноутбука ");
        note1.name = scanner.nextLine();
        System.out.println(" Введите название ОС ");
        note1.os = scanner.nextLine();
        System.out.println(" Введите цвет ");
        note1.color = scanner.nextLine();
        System.out.println(" Введите обьем оперативной памяти ");
        note1.size_op_mem = scanner.nextLine();
        System.out.println(" Введите обьем HDD ");
        note1.size_hard_drive = scanner.nextLine();
        System.out.println(" Введите цену ");
        note1.price = scanner.nextLine();
        notes.add(note1);
        WriteInFile(notes);
    }      
    static void WriteInFile ( ArrayList<Notebooks> notes){
        try (FileWriter fr = new FileWriter("file", true)) {
            for (Notebooks el : notes) {
                fr.write(el.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    static void Find () {                              //принимает на вход критерии поиска, помещает в HashMap, выводит результаты 
        boolean flag =true;
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> proper = new HashMap<String,String>();
        ArrayList<Notebooks> notes = GetArrayList();
        notes = GetArrayList();
        boolean flag2 = false;
        while (flag ==true) {
            System.out.println("Введите параметры поиска или f для завершения ввода критериев поиска и поиска по выбраным критериям :"); 
            System.out.println("1 - название"); 
            System.out.println("2 - ОС"); 
            System.out.println("3 - цвет"); 
            System.out.println("4 - обьем ОЗУ");
            System.out.println("5 - обьем жесткого диска");  
            System.out.println("f - приступить к поиску"+"\n");
            switch (scanner.next()) {
                case "1":
                System.out.println("Введите название для поиска ");
                proper.put("Name", scanner.next());  
                    break;
                case "2":
                System.out.println("Введите название ОС для поиска ");
                proper.put("OS", scanner.next());
                    break;
                case "3":
                System.out.println("Введите цвет для поиска ");
                proper.put("Color", scanner.next());
                    break;
                case "4":
                System.out.println("Введите размер озу для поиска ");
                proper.put("RAM", scanner.next()); 
                    break;
                case "5":
                System.out.println("Введите обьем HDD для поиска ");
                proper.put("HDD", scanner.next()); 
                    break; 
                case "f":
                flag = false;
                default:
                    break;
            }
            }  
            System.out.println("      Выбраны следующие параметры для поиска :");
            for (String el : proper.keySet()) {
                System.out.println(el+" -- "+ proper.get(el));
            } 
            for (Notebooks item : notes) {
                  if (Verify(proper, item)){
                    System.out.println(item);
                    flag2 = true;
                  }     
            }  
            if (flag2==false) System.out.println("По заданным параметрам ничего не найдено");
 }
    static ArrayList<String> ReadFileToArrStr (String filePath){       //читает содержимое файла в массив строк
        ArrayList<String> list = new ArrayList<>();
        File file = new File (filePath);
        try (Scanner scanner = new Scanner (file)) {
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            } 
         } catch (Exception e) {
            e.printStackTrace();
        }     
    return list;
}   
    static Notebooks StrToNote (String st){                           //разбивает строку из массива на элементы , создает элемент класса Notebooks
        String [] str = new String[6];
        Notebooks note = new Notebooks();
        str = st.split(" ");
        note.name = str[0];
        note.os = str[1];
        note.color = str[2];
        note.size_op_mem = str[3];
        note.size_hard_drive = str[4];
        note.price = str[5];
        return note;
    }
    static ArrayList<Notebooks> GetArrayList (){                     //добавляет элемент Noteboks в лист
        ArrayList<Notebooks> notes = new ArrayList<>();
        for (String item : ReadFileToArrStr("file")) {
            notes.add(StrToNote(item));
        }
      return notes;  
    }
    static boolean Verify (HashMap<String, String> map, Notebooks element) {   //проверяет соттветсвие элемента класса Notebooks на соответсвие параметрам поиска
        boolean flag = true;
        for (String el : map.keySet()) {
            if (el=="OS"&&!element.os.equals(map.get("OS"))) flag=false;
            else if (el=="Name"&&!element.name.equals(map.get("Name"))) flag=false;
            else if (el=="Color"&&!element.color.equals(map.get("Color"))) flag=false;
            else if (el=="RAM"&&!element.size_op_mem.equals(map.get("RAM"))) flag=false;
            else if (el=="HDD"&&!element.size_hard_drive.equals(map.get("HDD"))) flag=false;
        }
        return flag;
    }
      
}


