package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TicketManagerTest {

    @Mock
    TicketRepository repository;
    @InjectMocks
    private TicketManager manager;
    private Ticket flight1 = new Ticket(10000, "MOW", "LED");
    private Ticket flight2 = new Ticket(20000, "MOW", "LED");
    private Ticket flight3 = new Ticket(30000, "MOW", "GOJ");
    private Ticket flight4 = new Ticket(40000, "MOW", "GOJ");

    @Test
    void shouldFindTickets() {
        Ticket[] returned = new Ticket[]{flight1, flight2, flight3, flight4};
        doReturn(returned).when(repository).getAll();
        Ticket[] actual = manager.findTickets("MOW", "LED");
        Ticket[] expected = new Ticket[]{flight1, flight2};

        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldNotFindTickets(){
        Ticket[] returned = new Ticket[]{flight1, flight2, flight3, flight4};
        doReturn(returned).when(repository).getAll();
        Ticket[] actual = manager.findTickets("GOJ", "LED");
        Ticket[] expected = new Ticket[]{};

        assertArrayEquals(expected, actual);
    }
}