package com.example.managercourse.controller;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudyScheduler {
    public static void main(String[] args) {
        List<LocalDate> studyDays = getStudyDays(1, 5); // Example: 5 study sessions, starting from Monday, Wednesday, Friday
        System.out.println("Study days:");
        for (LocalDate studyDay : studyDays) {
            System.out.println(studyDay);
        }
    }

    public static List<LocalDate> getStudyDays(int selectedValue, int soBuoi) {
        List<LocalDate> studyDays = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        // Nếu ngày bắt đầu là một ngày học được chọn, chuyển sang ngày học tiếp theo
        if (selectedValue == 1 && (currentDate.getDayOfWeek() == DayOfWeek.MONDAY || currentDate.getDayOfWeek() == DayOfWeek.WEDNESDAY || currentDate.getDayOfWeek() == DayOfWeek.FRIDAY)) {
            currentDate = currentDate.plusDays(1);
        } else if (selectedValue == 2 && (currentDate.getDayOfWeek() == DayOfWeek.TUESDAY || currentDate.getDayOfWeek() == DayOfWeek.THURSDAY || currentDate.getDayOfWeek() == DayOfWeek.SATURDAY)) {
            currentDate = currentDate.plusDays(1);
        }

        // Đếm số buổi học đã được thêm vào danh sách
        int addedSessions = 0;

        // Kiểm tra từng ngày từ ngày bắt đầu đến khi đã thêm đủ số buổi học
        while (addedSessions < soBuoi) {
            // Kiểm tra ngày thứ và selectedValue
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            boolean shouldAdd = false;
            if (selectedValue == 1) {
                shouldAdd = (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.FRIDAY);
            } else if (selectedValue == 2) {
                shouldAdd = (dayOfWeek == DayOfWeek.TUESDAY || dayOfWeek == DayOfWeek.THURSDAY || dayOfWeek == DayOfWeek.SATURDAY);
            }

            if (shouldAdd) {
                studyDays.add(currentDate);
                addedSessions++; // Tăng số buổi học đã được thêm vào danh sách
            }

            // Di chuyển tới ngày tiếp theo
            currentDate = currentDate.plusDays(1);
        }

        return studyDays;
    }
}
