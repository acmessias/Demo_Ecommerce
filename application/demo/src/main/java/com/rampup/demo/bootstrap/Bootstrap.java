package com.rampup.demo.bootstrap;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rampup.demo.entities.*;
import com.rampup.demo.entities.enums.*;
import com.rampup.demo.services.*;

@Configuration
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CharacteristicService characteristicService;

    @Autowired
    private ProductOfferingService productOfferingService;

    @Autowired
    private OrderService orderService;

    public void run(String... args) throws Exception {

        // TIME PERIOD
        TimePeriod timePeriod1 = new TimePeriod(null, Instant.parse("2020-11-30T09:00:00.00Z"),
                Instant.parse("2021-11-30T23:59:00.00Z"), Instant.parse("2022-05-28T00:01:00.00Z"));

        // CHARACTERISTIC
        Characteristic characteristic1 = new Characteristic(null, "watercolor", timePeriod1,
                CharacteristicType.valueOf(1), "Value Type", true);
        Characteristic characteristic2 = new Characteristic(null, "Oil painting art supplie", timePeriod1,
                CharacteristicType.valueOf(3), "Value Type", true);

        characteristic1 = characteristicService.insert(characteristic1);
        characteristic2 = characteristicService.insert(characteristic2);

        // PRODUCT OFFERING
        ProductOffering productOffering1 = new ProductOffering(null, "Professional Watercolor Tube - 8ml", timePeriod1,
                POState.valueOf(1), true, true, 290.0);
        productOffering1.addCharacteristic(characteristic1);

        ProductOffering productOffering2 = new ProductOffering(null, "Pentel Oil Pastels 50/Pkg", timePeriod1,
                POState.valueOf(3), false, true, 100.90);
        productOffering2.addCharacteristic(characteristic2);

        ProductOffering productOffering3 = new ProductOffering(null, "Watercolor Brush", timePeriod1,
                POState.valueOf(2), false, true, 51.0);
        productOffering3.addCharacteristic(characteristic1);

        productOffering1 = productOfferingService.insert(productOffering1);
        productOffering2 = productOfferingService.insert(productOffering2);
        productOffering3 = productOfferingService.insert(productOffering3);

        // ROLE
        Role role1 = new Role(null, Authorities.valueOf(1));
        Role role2 = new Role(null, Authorities.valueOf(2));

        role1 = roleService.insert(role1);
        role2 = roleService.insert(role2);

        // Relationship Users - Roles

        List<Role> allRoles = new ArrayList<Role>();
        allRoles.add(role1);
        allRoles.add(role2);

        List<Role> admin = new ArrayList<Role>();
        admin.add(role1);

        List<Role> operator = new ArrayList<Role>();
        operator.add(role2);

        // USER
        
        User user1 = new User(null, "ana@gmail.com", "123456", admin, true);
        User user2 = new User(null, "gabriel07@gmail.com", "1bF45h", allRoles, true);
        User user3 = new User(null, "bruno@hotmail.com", "gHw4j8", operator, true);
        User user4 = new User(null, "helena24@gmail.com", "k40Hle", operator, true);
        User user5 = new User(null, "silviaoliveira@hotmail.com", "2yF34@", admin, true);
        User user6 = new User(null, "superadmin@gmail.com", "sup3r4dm1n", admin, true);

        user1 = userService.insert(user1);
        user2 = userService.insert(user2);
        user3 = userService.insert(user3);
        user4 = userService.insert(user4);
        user5 = userService.insert(user5);
        user6 = userService.insert(user6);

        // ADDRESS
        List<Address> address1 = new ArrayList<Address>();
        address1.add(new Address(null, "Street 1", 114, "Cidade Aracy", "Apto 13", "13563098", AddressType.valueOf(1),
                "Brazil"));
        address1.add(new Address(null, "Street 05", 112, "Jardim Aliança", "-", "13640384", AddressType.valueOf(1),
                "Brazil"));

        List<Address> address2 = new ArrayList<Address>();
        address2.add(
                new Address(null, "Street 23", 67, "Araucária", "-", "13073885", AddressType.valueOf(2), "Brazil"));

        List<Address> address3 = new ArrayList<Address>();
        address3.add(new Address(null, "Street 13", 230, "Santa Felícia", "Apto 101", "14145678",
                AddressType.valueOf(3), "Brazil"));

        List<Address> address4 = new ArrayList<Address>();
        address4.add(new Address(null, "Street 15", 455, "Villa Brasilia", "-", "13567328", AddressType.valueOf(1),
                "Brazil"));

        List<Address> address5 = new ArrayList<Address>();
        address5.add(new Address(null, "Street 19", 25, "Jardim Aliança", "Apto 304", "13067854",
                AddressType.valueOf(1), "Brazil"));

        // CUSTOMER
        Customer customer1 = new Customer(null, "Ana Paula Souza", "43817551037", "Active", CustomerType.valueOf(1),
                "Perfect", "123456", user1, address1, true);
        Customer customer2 = new Customer(null, "Gabriel Henrico", "50522517005", "Inactive", CustomerType.valueOf(1),
                "Good", "1bF45h", user2, address2, true);
        Customer customer3 = new Customer(null, "Bruno Campos", "54607610003", "Active", CustomerType.valueOf(2), "Bad",
                "gHw4j8", user3, address3, true);
        Customer customer4 = new Customer(null, "Helena pereira", "65890904086", "Inactive", CustomerType.valueOf(3),
                "Good", "k40Hle", user4, address4, true);
        Customer customer5 = new Customer(null, "Silvia Oliveira", "83231273013", "Active", CustomerType.valueOf(1),
                "Bad", "2yF34@", user5, address5, true);

        customer1 = customerService.insert(customer1);
        customer2 = customerService.insert(customer2);
        customer3 = customerService.insert(customer3);
        customer4 = customerService.insert(customer4);
        customer5 = customerService.insert(customer5);

        // PAYMENT
        Payment payment1 = new BankSlipPayment(null, PaymentState.DENIED, Instant.parse("2021-11-30T09:00:00.00Z"),
                Instant.parse("2021-12-02T23:59:00.00Z"));
        Payment payment2 = new CreditCardPayment(null, PaymentState.DENIED, 4);
        Payment payment3 = new CreditCardPayment(null, PaymentState.DENIED, 1);
        Payment payment4 = new BankSlipPayment(null, PaymentState.DENIED, Instant.parse("2022-10-09T06:30:17.00Z"),
                Instant.parse("2022-10-11T23:59:00.00Z"));
        Payment payment5 = new CreditCardPayment(null, PaymentState.DENIED, 3);

        // ORDER
        Order order1 = new Order(null, Instant.parse("2021-11-30T09:00:00.00Z"), customer1,
                customer1.getAddresses().get(0), payment1, true);
        Order order2 = new Order(null, Instant.parse("2022-01-10T08:39:15.00Z"), customer1,
                customer1.getAddresses().get(0), payment2, true);
        Order order3 = new Order(null, Instant.parse("2022-09-30T19:20:04.00Z"), customer4,
                customer1.getAddresses().get(0), payment3, true);
        Order order4 = new Order(null, Instant.parse("2022-10-09T06:30:17.00Z"), customer3,
                customer1.getAddresses().get(0), payment4, true);
        Order order5 = new Order(null, Instant.parse("2022-11-29T22:34:00.00Z"), customer4,
                customer1.getAddresses().get(0), payment5, true);

        // ORDER ITEM
        order1.addOrderItem(new OrderItem(order1, productOffering2, 2.00, 1));
        order1.addOrderItem(new OrderItem(order1, productOffering1, 10.00, 2));

        order2.addOrderItem(new OrderItem(order2, productOffering2, 6.00, 3));

        order3.addOrderItem(new OrderItem(order3, productOffering1, 10.00, 2));

        order4.addOrderItem(new OrderItem(order4, productOffering1, 15.00, 3));

        order5.addOrderItem(new OrderItem(order5, productOffering2, 4.00, 2));
        order5.addOrderItem(new OrderItem(order5, productOffering1, 15.00, 3));

        order1 = orderService.insert(order1);
        order2 = orderService.insert(order2);
        order3 = orderService.insert(order3);
        order4 = orderService.insert(order4);
        order5 = orderService.insert(order5);
    }
}