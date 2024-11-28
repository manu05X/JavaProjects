package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.FakeStoreUserAddressDto;
import org.example.evaluations.evaluation.dtos.FakeStoreUserCreatedResponseDto;
import org.example.evaluations.evaluation.dtos.FakeStoreUserDto;
import org.example.evaluations.evaluation.dtos.FakeStoreUserNameDto;
import org.example.evaluations.evaluation.models.Address;
import org.example.evaluations.evaluation.models.Name;
import org.example.evaluations.evaluation.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements IUserService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public User add(User user) {
        FakeStoreUserDto fakeStoreUserRequestDtoInput = from(user);
        FakeStoreUserCreatedResponseDto fakeStoreUserCreatedResponseDto = requestForEntity("https://fakestoreapi.com/users",HttpMethod.POST, fakeStoreUserRequestDtoInput, FakeStoreUserCreatedResponseDto.class).getBody();
        user.setId(fakeStoreUserCreatedResponseDto.getId());
        return user;
    }

    @Override
    public User update(User user, Long userId) {
        FakeStoreUserDto fakeStoreUserRequestDtoInput = from(user);
        FakeStoreUserDto fakeStoreUserRequestDtoResponse = requestForEntity("https://fakestoreapi.com/users/{userId}",HttpMethod.PUT, fakeStoreUserRequestDtoInput, FakeStoreUserDto.class,userId).getBody();
        User userResponse = from(fakeStoreUserRequestDtoResponse);
        return userResponse;
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private FakeStoreUserDto from(User user) {
        FakeStoreUserDto fakeStoreUserRequestDto = new FakeStoreUserDto();
        fakeStoreUserRequestDto.setUsername(user.getUsername());
        fakeStoreUserRequestDto.setPassword(user.getPassword());
        fakeStoreUserRequestDto.setEmail(user.getEmail());
        if(user.getAddress() != null) {
            FakeStoreUserNameDto name = new FakeStoreUserNameDto();
            name.setFirstname(user.getName().getFirstname());
            name.setLastname(user.getName().getLastname());
            fakeStoreUserRequestDto.setName(name);
        }
        if(user.getName() != null) {
            FakeStoreUserAddressDto address = new FakeStoreUserAddressDto();
            address.setCity(user.getAddress().getCity());
            address.setStreet(user.getAddress().getStreet());
            address.setNumber(user.getAddress().getNumber());
            fakeStoreUserRequestDto.setAddress(address);
        }
        return fakeStoreUserRequestDto;
    }



    private User from(FakeStoreUserDto fakeStoreUserRequestDto) {
        User user = new User();

        user.setUsername(fakeStoreUserRequestDto.getUsername());
        user.setPassword(fakeStoreUserRequestDto.getPassword());
        user.setEmail(fakeStoreUserRequestDto.getEmail());
        if(fakeStoreUserRequestDto.getAddress() != null) {
            Name name = new Name();
            name.setFirstname(fakeStoreUserRequestDto.getName().getFirstname());
            name.setLastname(fakeStoreUserRequestDto.getName().getLastname());
            user.setName(name);
        }

        if(fakeStoreUserRequestDto.getName() != null) {
            Address address = new Address();
            address.setCity(fakeStoreUserRequestDto.getAddress().getCity());
            address.setStreet(fakeStoreUserRequestDto.getAddress().getStreet());
            address.setNumber(fakeStoreUserRequestDto.getAddress().getNumber());
            user.setAddress(address);
        }

        return user;
    }
}
