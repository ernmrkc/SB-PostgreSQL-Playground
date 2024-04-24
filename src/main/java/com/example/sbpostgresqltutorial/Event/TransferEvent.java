package com.example.sbpostgresqltutorial.Event;

import com.example.sbpostgresqltutorial.Transaction.Model.TransferDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TransferEvent extends ApplicationEvent {
    private final TransferDTO transferDTO;

    public TransferEvent(Object source, TransferDTO transferDTO) {
        super(source);
        this.transferDTO = transferDTO;
    }

}
