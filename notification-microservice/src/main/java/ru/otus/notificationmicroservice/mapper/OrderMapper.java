package ru.otus.notificationmicroservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.notificationmicroservice.model.Order;
import ru.otus.notificationmicroservice.model.OrderMessageDto;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    Order toOrder(OrderMessageDto orderMessageDto);
}
