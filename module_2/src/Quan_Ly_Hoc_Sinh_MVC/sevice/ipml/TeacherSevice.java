package Quan_Ly_Hoc_Sinh_MVC.sevice.ipml;

import Quan_Ly_Hoc_Sinh_MVC.Model.Teacher;
import Quan_Ly_Hoc_Sinh_MVC.sevice.ITeacherSevice;
import Quan_Ly_Hoc_Sinh_MVC.sevice.exception.NameException;
import Quan_Ly_Hoc_Sinh_MVC.sevice.exception.check;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherSevice implements ITeacherSevice {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Teacher> teacherList = new ArrayList<>();

    @Override
    public void addTeacher() {
        Teacher teacher = this.infoTeacher();
        teacherList.add(teacher);
        System.out.println("Thêm mới thành công");
    }

    @Override
    public void displayAllTeacher() {
        for (Teacher teacher : teacherList) {
            System.out.println(teacher);
        }
    }

    @Override
    public void removeTeacher() {
        System.out.println("Mời bạn nhập mã giáo viên cần xóa: ");
        String code = scanner.nextLine();
        boolean flagDelete = false;
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getCode().equals(code)) {
                System.out.println("Bạn có chắc muốn xóa giáo viên này không? Nhập Y: yes, N: no");
                String choice = scanner.nextLine();
                if (choice.equals("Y")) {
                    teacherList.remove(i);
                    System.out.println("Xóa thành công");
                }
                flagDelete = true;
                break;
            }
        }
        if (!flagDelete) {
            System.out.println("Không tìm thấy đối tượng cần xóa.");
        }
    }

    @Override
    public void searchNameTeacher() {
        int count = 0;
        System.out.println("Vui lòng nhập ký tự có trong tên hoặc tên Giáo Viên muốn tìm");
        String name = scanner.nextLine();
        for (Teacher teacher : teacherList) {
            if (teacher.getName().contains(name)) {
                System.out.println(teacher);
                count++;
            }
        }
        if (count < 0) {
            System.out.println("Không có sản phẩm cần tìm kiếm trong Menu" + name);
        }
    }

    @Override
    public void searchIdTeacher() {
        int count = 0;
        System.out.println("Vui lòng nhập ID Giáo Viên muốn tìm");
        String id = scanner.nextLine();
        for (Teacher teacher : teacherList) {
            if (teacher.getCode().equals(id)) {
                System.out.println(teacher);
                count++;
            }
        }
        if (count < 0) {
            System.out.println("Không có sản phẩm cần tìm kiếm trong Menu" + id);
        }

    }

    @Override
    public void sortNameTeacher() {
        boolean isSwap = true;
        Teacher teacher;
        for (int i = 0; i < teacherList.size() - 1 && isSwap; i++) {
            isSwap = false;
            for (int j = 0; j < teacherList.size() - 1 - i; j++) {
                int compareName = teacherList.get(j).getName().compareTo(teacherList.get(j + 1).getName());
                if (compareName > 0) {
                    isSwap = true;
                    teacher = teacherList.get(j + 1);
                    teacherList.set(j + 1, teacherList.get(j));
                    teacherList.set(j, teacher);
                }
                if (compareName == 0) {
                    int compareId = teacherList.get(j).getCode().compareTo(teacherList.get(j + 1).getCode());
                    if (compareId > 0) {
                        isSwap = true;
                        teacher = teacherList.get(j + 1);
                        teacherList.set(j + 1, teacherList.get(j));
                        teacherList.set(j, teacher);
                    }
                }
            }
        }
        displayAllTeacher();
    }

    @Override
    public void fakeNameTeacher() {
        System.out.println("Cập nhật danh sách giả lập thành công, vui lòng ấn 2 để hiển thị danh sách");
        teacherList.add(new Teacher("CG1231", "Trung DP", "Nam", "GĐ đào tạo"));
        teacherList.add(new Teacher("CG2233", "Chánh TT", "Nam", "Giảng viên"));
        teacherList.add(new Teacher("CG3234", "Công NT", "Nam", "Giảng viên"));
        teacherList.add(new Teacher("CG4234", "Hải TT", "Nam", "Coach"));
        teacherList.add(new Teacher("CG5238", "Quang NN", "Nữ", "Intructor"));
        teacherList.add(new Teacher("CG6237", "Trung DC", "Nam", "Giảng viên"));
        teacherList.add(new Teacher("CG7236", "Tiến", "Nữ", "Giảng viên"));
    }


    public Teacher infoTeacher() {
        String code;
        while (true) {
            System.out.println("Mời bạn nhập mã giáo viên");
            code = scanner.nextLine();
            boolean check = false;
            for (Teacher teacher : teacherList) {
                if (teacher.getCode().equals(code)) {
                    check = true;
                    System.out.println("Mã này đã tồn tại");
                }
            }
            if (!check) {
                break;
            }

        }
        String name = "";
        while (true){
            try {
                System.out.print("Mời bạn nhập tên giáo viên ");
                name = scanner.nextLine();
                for (int i = 0; i <name.length() ; i++) {
                    if(Character.isDigit(name.charAt(i))) {
                        throw new NameException("Tên không được chứa số");
                    }
                }
                break;
            } catch (NameException e) {
                System.out.println(e.getMessage());

            }
        }
        String gender;
        while (true) {
            System.out.println("Nhập giới tính");
            try {
                gender = scanner.nextLine();
                if(!(gender.equals("Nam") || gender.equals("Nữ"))) {
                    throw new check("Giới tính không hợp lệ");
                }
                break;
            } catch (check e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Mời bạn nhập chuyên môn");
        String nameSpecialize = scanner.nextLine();
        Teacher teacher;
        teacher = new Teacher(code, name, gender, nameSpecialize);
        return teacher;
    }

}
