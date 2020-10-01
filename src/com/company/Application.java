package com.company;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Application {

    private static final String TIMEZONE = "Europe/Paris";
    public static void main(String[] args) {

        List<User> userList = new ArrayList<>();

        userList.add(new User("admin", "admin", 100, "admin@mail.com", getTimeNow(), UserEnum.NEW));
        userList.add(new User("Name", "Last name", 22, "name@mail.com", getTimeMinusDays(1), UserEnum.NEW));
        userList.add(new User("Jora", "Cincilei", 15, "jora@mail.com", getTimeMinusDays(2), UserEnum.NEW));
        userList.add(new User("Fiodor", "Mutu", 25, "fiodor@mail.com", getTimeMinusWeeks(1), UserEnum.NEW));
        userList.add(new User("Tudor", "Istrati", 66, "tudor@mail.com", getTimeMinusWeeks(2), UserEnum.NEW));
        userList.add(new User("Aliona", "Para", 27, "tudor@mail.com", getTimeMinusWeeks(3), UserEnum.NEW));
        userList.add(new User("Gicu", "Afanasie", 17, "gicu@mail.com", getTimeMinusMonths(1), UserEnum.NEW));
        userList.add(new User("Ana", "Moisei", 44, "moisei@mail.com", getTimeMinusMonths(2), UserEnum.INACTIVE));
        userList.add(new User("Maria", "Farima", 33, "maria@mail.com", getTimeMinusMonths(3), UserEnum.INACTIVE));
        userList.add(new User("Efim", "Tesla", 29, "efim@mail.com", getTimeMinusMonths(4), UserEnum.INACTIVE));

        //Userii creati
        userList.stream()
                .forEach(System.out::println);
        System.out.println("");

        userList.stream()
                .filter(user -> user.getUserEnum().equals(UserEnum.NEW))
                .filter(user -> checkForUsersMoreThanADay(user.getTimestamp()))
                .forEach(user -> user.setUserEnum(UserEnum.ACTIVE));

        //Userii creati cu status modificat in active
        userList.stream()
                .forEach(System.out::println);
        System.out.println("");

        userList.stream()
                .filter(user -> user.getUserEnum().equals(UserEnum.INACTIVE))
                .forEach(user -> user.setUserEnum(UserEnum.BLOCKED));

        //Userii creati cu status modificat in blocked
        userList.stream()
                .forEach(System.out::println);
    }

    public static boolean checkForUsersMoreThanADay(Long timestamp) {
        LocalDateTime currentUserTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());
        final int userYear = currentUserTime.getYear();
        final int userDayOfYear = currentUserTime.getDayOfYear();

        final LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), TimeZone.getDefault().toZoneId());
        final int currentYear = localDateTime.getYear();
        final int currentDayOfYear = localDateTime.getDayOfYear();

        return ((userYear == currentYear) || (userYear < currentYear)) && userDayOfYear < currentDayOfYear;
    }

    public static long getTimeNow() {
        return ZonedDateTime.now(ZoneId.of(TIMEZONE)).toLocalDateTime().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static long getTimeMinusDays(int days) {
        return ZonedDateTime.now(ZoneId.of(TIMEZONE)).minusDays(days).toLocalDateTime().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static long getTimeMinusMonths(int months) {
        return ZonedDateTime.now(ZoneId.of(TIMEZONE)).minusMonths(months).toLocalDateTime().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static long getTimeMinusWeeks(int weeks) {
        return ZonedDateTime.now(ZoneId.of(TIMEZONE)).minusWeeks(weeks).toLocalDateTime().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

}