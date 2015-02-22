package com.sky.assignment.service.impl;

import static com.sky.assignment.common.SkyComponents.Services.AUTH_SERVICE;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.sky.assignment.exception.MandatoryResourceMissingException;
import com.sky.assignment.model.UserDto;
import com.sky.assignment.model.UserInfoDto;
import com.sky.assignment.service.AuthService;

/**
 * Default authentication service, verifying the user credentials against the predefined JSON file.
 * 
 * @author switalski
 */
@Service(AUTH_SERVICE)
public class DefaultAuthService implements AuthService {
	
	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(DefaultAuthService.class);

	/** Path to users.json file. */
	private static final String USERS_JSON_FILE_PATH = "/data/users.json";

	/** List of all users. */
	private List<UserDto> users = Lists.newArrayList();
	
	/**
	 * Auto-initializes this service by reading users data from the predefined file.
	 * 
	 * @throws MandatoryResourceMissingException
	 *             If users file could not be found
	 * @throws IOException
	 *             If users file could not be read
	 */
	@PostConstruct
	protected void loadUserData() throws MandatoryResourceMissingException, IOException {
		loadUserData(USERS_JSON_FILE_PATH);
	}

	/**
	 * Loads users data from the JSON file with given path.
	 * 
	 * @param path
	 *            Path to JSON file
	 * 
	 * @throws MandatoryResourceMissingException
	 *             If users file could not be found
	 * @throws IOException
	 *             If users file could not be read
	 * 
	 **/
	protected void loadUserData(String path) throws MandatoryResourceMissingException, IOException {
		LOG.info("Loading users data from: {}", path);
		InputStream inputStream = DefaultAuthService.class.getResourceAsStream(path);
		if (inputStream == null) {
			throw new MandatoryResourceMissingException("Users data file " + path + " is missing!");
		}
		List<UserDto> readValue = new ObjectMapper().readValue(inputStream, new TypeReference<List<UserDto>>() {
		});
		this.users = Lists.newArrayList(readValue);
		LOG.info("Loaded {} users", users.size());
	}
	
	/**
	 * Performs authentication for given username (case insensitive) and password (case sensitive).
	 * 
	 * @return User role if authenticated successfully, null otherwise.
	 */
	@Override
	public UserInfoDto authenticate(String username, String password) {
		Optional<UserDto> result = users.parallelStream() //
				.filter(user -> StringUtils.equalsIgnoreCase(username, user.getCredentials().getUsername())) //
				.filter(user -> StringUtils.equals(password, user.getCredentials().getPassword())) //
				.findFirst();
		if (result.isPresent()) {
			return new UserInfoDto(result.get());
		} else {
			return null;
		}
	}
	
	protected List<UserDto> getUsers() {
		return users;
	}
}
