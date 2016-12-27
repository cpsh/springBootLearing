package com.example;

import com.example.controller.HelloController;
import com.example.controller.ReadingListController;
import com.example.entity.Book;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by shichp on 2016/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringApplicationConfiguration(classes = MockServletContext.class)
@SpringApplicationConfiguration(classes = SpringbootDemoApplication.class)
@WebAppConfiguration  //开启Web上下文测试
public class SpringMVCMockTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    /**
     * 要在测试里设置Mock MVC，可以使用MockMvcBuilders，该类提供了两个静态方法。
      standaloneSetup()：构建一个Mock MVC，提供一个或多个手工创建并配置的控制器。
      webAppContextSetup()：使用Spring应用程序上下文来构建Mock MVC，该上下文里
     可以包含一个或多个配置好的控制器。
     两者的主要区别在于，standaloneSetup()希望你手工初始化并注入你要测试的控制器，
     而webAppContextSetup()则基于一个WebApplicationContext的实例，通常由Spring加载。
     前者同单元测试更加接近，你可能只想让它专注于单一控制器的测试，而后者让Spring加载控制
     器及其依赖，以便进行完整的集成测试。
     */
    @Before
    public void setupMockMvc(){
        //设置mockMVC
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    //    mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void homePage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/readingList/cpsh"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", Matchers.is(Matchers.empty())));
    }

    @Test
    public void getHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }

    @Test
    public void getIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello/index").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("host"))
                .andExpect(model().attribute("host",Matchers.is("http://blog.didispace.com")));
    }

    @Test
    public void contextLoads() {
    }


}
