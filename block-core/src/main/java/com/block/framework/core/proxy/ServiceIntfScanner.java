package com.block.framework.core.proxy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

public class ServiceIntfScanner extends ClassPathBeanDefinitionScanner {

	public ServiceIntfScanner(BeanDefinitionRegistry registry) {
		super(registry,false);
		// TODO Auto-generated constructor stub
	}
	
	public void registerFilters() {
	    boolean acceptAllInterfaces = true;

//	    // if specified, use the given annotation and / or marker interface
//	    if (this.annotationClass != null) {
//	      addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
//	      acceptAllInterfaces = false;
//	    }
//
//	    // override AssignableTypeFilter to ignore matches on the actual marker interface
//	    if (this.markerInterface != null) {
//	      addIncludeFilter(new AssignableTypeFilter(this.markerInterface) {
//	        @Override
//	        protected boolean matchClassName(String className) {
//	          return false;
//	        }
//	      });
//	      acceptAllInterfaces = false;
//	    }

	    if (acceptAllInterfaces) {
	      // default include filter that accepts all classes
	      addIncludeFilter(new TypeFilter() {
	        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
	          return true;
	        }
	      });
	    }

	    // exclude package-info.java
	    addExcludeFilter(new TypeFilter() {
	      public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
	        String className = metadataReader.getClassMetadata().getClassName();
	        return className.endsWith("package-info");
	      }
	    });
	  }

	@Override
	  public Set<BeanDefinitionHolder> doScan(String... basePackages) {
	    Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

	    if (beanDefinitions.isEmpty()) {
	      logger.warn("No block service interface was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
	    } else {
	      for (BeanDefinitionHolder holder : beanDefinitions) {
	        GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();

	        if (logger.isDebugEnabled()) {
	          logger.debug("Creating service proxy with name '" + holder.getBeanName() 
	              + "' and '" + definition.getBeanClassName() + "' interface");
	        }

	        // the mapper interface is the original class of the bean
	        // but, the actual class of the bean is MapperFactoryBean
	        definition.getPropertyValues().add("serviceInterface", definition.getBeanClassName());
	        definition.setBeanClass(ProxyFactoryBean.class);

	        
	      }
	    }

	    return beanDefinitions;
	  }
}
