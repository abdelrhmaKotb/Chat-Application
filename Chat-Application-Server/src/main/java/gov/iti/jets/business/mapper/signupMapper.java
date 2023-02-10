package gov.iti.jets.business.mapper;

public interface signupMapper<E,R> {
    //R getContactDto(E entity);
    R toDto(E e);
    E toEntity(R r);
}
