package com.renison.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.renison.exception.BadRequestException;
import com.renison.exception.InternalErrorException;
import com.renison.exception.ProctorException;
import com.renison.jackson.View.Admin;
import com.renison.model.Answer;
import com.renison.model.HtmlComponent;
import com.renison.model.Question;
import com.renison.model.TestComponent;
import com.renison.model.VideoComponent;
import com.renison.util.JsonUtil;

@RestController
@RequestMapping("/testComponents")
@CrossOrigin("*")
public class TestComponentController extends BaseController<TestComponent> {
	public static final String ROOT = "public/";

	@Override
	protected Class<TestComponent> getResourceType() {
		return TestComponent.class;
	}

	@Override
	@JsonView(Admin.class)
	public List<TestComponent> findAll() {
		throw new ProctorException(63888252874l, "Should not query testComponents here, use category resource", "");
	}

	// only handle video files for now
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody ObjectNode uploadFile(@RequestParam("videoFile") MultipartFile file) {
		String fileName;
		if (!file.isEmpty()) {
			if (!file.getOriginalFilename().endsWith(".mp4")) {
				throw new BadRequestException(1236810l, "Does not support mp4", "Please upload only mp4 file");
			}
			try {
				fileName = (System.currentTimeMillis() + "-" + file.getOriginalFilename());
				Path storingPath = Paths.get(ROOT, fileName);
				Files.copy(file.getInputStream(), storingPath);
			} catch (IOException | RuntimeException e) {
				e.printStackTrace();
				throw new BadRequestException(12388564l, "Bad file to be processed", "cannot process file");
			}
		} else {
			throw new BadRequestException(12353367l, "Empty file", "empty file");
		}
		// we need to convert the file path to a URL that static file
		// handler recognizes and can serve
		String url = "/" + fileName;
		ObjectNode objectNode = JsonUtil.newJsonObject();
		objectNode.put("fileUrl", url);
		return objectNode;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody TestComponent update(@PathVariable Long id, @RequestBody TestComponent json) {
		// assume that json already has id and all other fields set, we replace
		// testcomponent in database with this.
		Session session = sessionFactory.getCurrentSession();
		json.setId(null);// remove id
		TestComponent c = get(id); // id is
		// make sure all fields are overriden
		if (json instanceof Question && c instanceof Question) {
			Question questionJson = (Question) json;
			Question questionInDb = (Question) c;
			questionInDb.setContent(questionJson.getContent());
			for (Answer oldAnswer : questionInDb.getAnswers()) {
				session.delete(oldAnswer);
			}
			questionInDb.getAnswers().clear();
			// erase all new answer ids
			for (Answer newAnswer : questionJson.getAnswers()) {
				newAnswer.setId(null);
			}
			questionInDb.getAnswers().addAll(questionJson.getAnswers());
		} else if ((json instanceof HtmlComponent) && c instanceof HtmlComponent) {
			HtmlComponent compJson = (HtmlComponent) json;
			HtmlComponent compInDb = (HtmlComponent) c;
			compInDb.setContent(compJson.getContent());
		} else if ((json instanceof VideoComponent) && c instanceof VideoComponent) {
			VideoComponent compJson = (VideoComponent) json;
			VideoComponent compInDb = (VideoComponent) c;
			compInDb.setContent(compJson.getContent());
		} else {
			throw new InternalErrorException(6565189333l, "Cannot find component type", "");
		}
		session.update(c);
		return c;
	}

}
