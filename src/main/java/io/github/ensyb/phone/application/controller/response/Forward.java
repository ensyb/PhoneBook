package io.github.ensyb.phone.application.controller.response;

public class Forward implements Response{
	
	private final String viewFileSufix = ".jsp";
	private final String viewBasePath = "WEB-INF/view/";
	private String resolvedPath;

	public Forward(final String target) {
		this.resolvedPath = this.resolveForRedirect(target);
		
	}
	
	public String consumePath(){
		return this.resolvedPath;
	}
	
	private String resolveForRedirect(final String target){
		String startsWithWebInf = "WEB-INF/";
		if(target.startsWith(startsWithWebInf)){
			if(!target.endsWith(viewFileSufix))
				return target + viewFileSufix;
				
			return target;
			
		}
		StringBuilder pathBuilder = new StringBuilder(viewBasePath);
		pathBuilder.append((target.charAt(0) == '/') ? target.substring(1, target.length()) : target);
		if(pathBuilder.indexOf(viewFileSufix) < 1)
			pathBuilder.append(viewFileSufix);
		return pathBuilder.toString();
	}
}
