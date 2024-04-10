package control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import model.Appointment;
import model.AppointmentCollection;

public class MyCalendar {
    private AppointmentCollection allAppointments;

    public MyCalendar() {
        this.allAppointments = new AppointmentCollection();
    }

    public void add(Appointment app) {
        allAppointments.add(app);
    }

    public AppointmentCollection getAllAppointments() {
        return allAppointments;
    }

    public AppointmentCollection getDayAppointments(LocalDate date) {
        AppointmentCollection dayAppointments = new AppointmentCollection();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime startOfNextDay = date.plusDays(1).atStartOfDay();

        for (int i = 0; i < allAppointments.size(); i++) {
            Appointment appointment = allAppointments.get(i);
            LocalDateTime appointmentStart = appointment.getFrom();
            LocalDateTime appointmentEnd = appointment.getTo();

            if (appointmentStart.isBefore(startOfNextDay) && appointmentEnd.isAfter(startOfDay)) {
                dayAppointments.add(appointment);
            }
        }

        return dayAppointments;
    }

    public AppointmentCollection getMonthAppointments(LocalDate date) {
        AppointmentCollection monthAppointments = new AppointmentCollection();
        LocalDateTime startOfMonth = date.withDayOfMonth(1).atStartOfDay();
        LocalDateTime startOfNextMonth = date.plusMonths(1).withDayOfMonth(1).atStartOfDay();

        for (int i = 0; i < allAppointments.size(); i++) {
            Appointment appointment = allAppointments.get(i);
            LocalDateTime appointmentStart = appointment.getFrom();
            LocalDateTime appointmentEnd = appointment.getTo();

            if (appointmentStart.isBefore(startOfNextMonth) && appointmentEnd.isAfter(startOfMonth)) {
                monthAppointments.add(appointment);
            }
        }

        return monthAppointments;
    }

    public AppointmentCollection getWeekAppointments(LocalDate date) {
        AppointmentCollection weekAppointments = new AppointmentCollection();
        LocalDateTime startOfWeek = date.with(java.time.DayOfWeek.MONDAY).atStartOfDay();
        LocalDateTime endOfWeek = date.with(java.time.DayOfWeek.SUNDAY).plusDays(1).atStartOfDay();

        for (int i = 0; i < allAppointments.size(); i++) {
            Appointment appointment = allAppointments.get(i);
            LocalDateTime appointmentStart = appointment.getFrom();
            LocalDateTime appointmentEnd = appointment.getTo();

            if (appointmentStart.isBefore(endOfWeek) && appointmentEnd.isAfter(startOfWeek)) {
                weekAppointments.add(appointment);
            }
        }

        return weekAppointments;
    }

    public boolean isOverlapped(LocalDateTime start, LocalDateTime end, LocalDateTime refStart, LocalDateTime refEnd) {
        // Controlla se uno dei limiti di tempo del primo periodo è contenuto nel secondo periodo
        boolean isStartContained = (refStart.isBefore(end) || refStart.isEqual(end)) && (refStart.isAfter(start) || refStart.isEqual(start));
        boolean isEndContained = (refEnd.isBefore(end) || refEnd.isEqual(end)) && (refEnd.isAfter(start) || refEnd.isEqual(start));

        // Controlla se uno dei limiti di tempo del secondo periodo è contenuto nel primo periodo
        boolean isRefStartContained = (start.isBefore(refEnd) || start.isEqual(refEnd)) && (start.isAfter(refStart) || start.isEqual(refStart));
        boolean isRefEndContained = (end.isBefore(refEnd) || end.isEqual(refEnd)) && (end.isAfter(refStart) || end.isEqual(refStart));

        // Restituisci true se almeno un limite di tempo di uno dei periodi è contenuto nell'altro periodo
        return isStartContained || isEndContained || isRefStartContained || isRefEndContained;
    }

    public boolean remove(Appointment app) {
        int index = allAppointments.indexOf(app);
        if (index != -1) {
            allAppointments.remove(index);
            return true; // L'appuntamento è stato rimosso con successo
        } else {
            return false; // L'appuntamento non è stato trovato nella collezione
        }
    }
}
