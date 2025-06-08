import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "HR", 60000, LocalDate.of(2018, 5, 10), Arrays.asList("Communication", "Recruitment")),
            new Employee("Bob", "IT", 85000, LocalDate.of(2019, 3, 15), Arrays.asList("Java", "Spring")),
            new Employee("Charlie", "IT", 95000, LocalDate.of(2017, 1, 20), Arrays.asList("Java", "Docker")),
            new Employee("Diana", "Finance", 72000, LocalDate.of(2020, 7, 5), Arrays.asList("Accounting", "Excel")),
            new Employee("Eve", "HR", 50000, LocalDate.of(2021, 9, 30), Arrays.asList("Training", "Compliance"))
        );

        System.out.println("Employees earning more than $75,000:");
        employees.stream()
                .filter(e -> e.getSalary() > 75000)
                .map(Employee::getName)
                .forEach(System.out::println);
                System.out.println("\nDepartments:");
employees.stream()
        .map(Employee::getDepartment)
        .distinct()
        .sorted()
        .forEach(System.out::println);
        double totalSalary = employees.stream()
        .mapToDouble(Employee::getSalary)
        .sum();
System.out.println("\nTotal Salary: $" + totalSalary);
System.out.println("\nAverage Salary Per Department:");
Map<String, Double> avgSalary = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
avgSalary.forEach((dept, avg) -> System.out.println(dept + ": $" + avg));
System.out.println("\nTop 3 Earners:");
employees.stream()
        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
        .limit(3)
        .map(Employee::getName)
        .forEach(System.out::println);
System.out.println("\nSkills Inventory:");
employees.stream()
        .flatMap(e -> e.getSkills().stream())
        .distinct()
        .sorted()
        .forEach(System.out::println);
System.out.println("\nEmployees Per Department:");
Map<String, String> deptMap = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.mapping(Employee::getName, Collectors.joining(", "))));
deptMap.forEach((dept, names) -> System.out.println(dept + ": " + names));
System.out.println("\nTop 3 Highest-Paid Employees:");
employees.stream()
        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
        .limit(3)
        .map(Employee::getName)
        .forEach(System.out::println);
System.out.println("\nDepartment Report (Department -> Employee Names):");
Map<String, String> departmentReport = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(Employee::getName, Collectors.joining(", "))
        ));

departmentReport.forEach((dept, names) ->
        System.out.println(dept + ": " + names));
System.out.println("\nSalary Bands:");
Map<String, List<Employee>> salaryBands = employees.stream()
        .collect(Collectors.groupingBy(e -> {
            double s = e.getSalary();
            if (s <= 50000) return "0-50k";
            else if (s <= 80000) return "50k-80k";
            else return "80k+";
        }));

salaryBands.forEach((band, empList) -> {
    System.out.println(band + ":");
    empList.forEach(e -> System.out.println("  " + e.getName() + " ($" + e.getSalary() + ")"));
});
System.out.println("\nTop Talent in Each Department:");
Map<String, Optional<Employee>> topTalent = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
        ));

topTalent.forEach((dept, emp) -> 
    System.out.println(dept + ": " + emp.map(Employee::getName).orElse("None")));
System.out.println("\nAll Unique Skills:");
employees.stream()
        .flatMap(e -> e.getSkills().stream())
        .distinct()
        .sorted()
        .forEach(System.out::println);




    }
        



}
