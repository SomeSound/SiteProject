package br.com.hyper.services;

import br.com.hyper.dtos.requests.LoginRequestDTO;
import br.com.hyper.dtos.responses.LoginResponseDTO;
import br.com.hyper.dtos.responses.TokenResponseDTO;
import br.com.hyper.dtos.responses.pages.CustomerPageResponseDTO;
import br.com.hyper.entities.SubscriptionEntity;
import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.responses.CustomerResponseDTO;
import br.com.hyper.entities.CustomerEntity;
import br.com.hyper.enums.UserRole;
import br.com.hyper.exceptions.CustomerException;
import br.com.hyper.repositories.CustomerRepository;
import br.com.hyper.dtos.requests.CustomerRequestDTO;
import br.com.hyper.repositories.SubscriptionRepository;
import br.com.hyper.utils.JwtTokenUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customer) {
        CustomerEntity customerEntity;
        try {
            SubscriptionEntity subscription = subscriptionRepository.findById(customer.getSubscription()).orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

            if (customerRepository.findByEmail(customer.getEmail()).isPresent()){
                throw new CustomerException(ErrorCodes.DUPLICATED_DATA, ErrorCodes.DUPLICATED_DATA.getMessage());
            }

            customerEntity = modelMapper.map(customer, CustomerEntity.class);

            customerEntity.setRole(UserRole.CUSTOMER);
            customerEntity.setSubscription(subscription);
            customerEntity.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
            customerEntity = customerRepository.save(customerEntity);

            return modelMapper.map(customerEntity, CustomerResponseDTO.class);
        }  catch (DataIntegrityViolationException e) {
            throw new CustomerException(ErrorCodes.DUPLICATED_DATA,
                    ErrorCodes.DUPLICATED_DATA.getMessage());
        }
    }

    @Override
    public CustomerResponseDTO findByEmail(String email) {

        CustomerEntity customerEntity = findByEmailOrThrowUserDataNotFoundException(email);

        return modelMapper.map(customerEntity, CustomerResponseDTO.class);

    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest, HttpServletResponse http) {

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication auth = authenticationManager.authenticate(login);

        String token = jwtTokenUtil.generateToken((CustomerEntity) auth.getPrincipal());

        TokenResponseDTO tokenResponse = new TokenResponseDTO();
        tokenResponse.setToken(token);

        CustomerEntity customer = findByEmailOrThrowUserDataNotFoundException(loginRequest.getEmail());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(tokenResponse);

        modelMapper.map(customer, response);

        return response;
    }

    @Override
    public TokenResponseDTO refreshToken(String token) {

        String newToken = jwtTokenUtil.refreshToken(token);

        TokenResponseDTO response = new TokenResponseDTO();

        response.setToken(newToken);

        return response;
    }

    @Override
    public CustomerPageResponseDTO findAll(Pageable pageable) {

        Page<CustomerEntity> customerEntities = customerRepository.findAll(pageable);

        return modelMapper.map(customerEntities, CustomerPageResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO update(Long id, CustomerRequestDTO user) {
        CustomerEntity userCurrent = findByIdOrThrowUserDataNotFoundException(id);

        userCurrent.setName(user.getName());
        userCurrent.setAvatar(user.getAvatar());

        customerRepository.save(userCurrent);

        return modelMapper.map(userCurrent, CustomerResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        CustomerEntity userCurrent = findByIdOrThrowUserDataNotFoundException(id);

        modelMapper.map(userCurrent, CustomerResponseDTO.class);
        customerRepository.delete(userCurrent);
    }

    private CustomerEntity findByIdOrThrowUserDataNotFoundException(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

    private CustomerEntity findByEmailOrThrowUserDataNotFoundException(String email) {
        return customerRepository.findByEmail(email).orElseThrow(
                () -> new CustomerException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}
