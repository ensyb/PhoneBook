package io.github.ensyb.phone.application.controller;

public class ViewModel {

	private final String viewFileSufix = ".jsp";
	private final String viewBasePath = "WEB-INF/view/";
	private final boolean forRedirect;
	private final String resolvedPath; 
	
	public ViewModel(final String target) {
		this.resolvedPath = resolveForRedirect(target);
		this.forRedirect = false;
	}
	
	public ViewModel(final String target, boolean redirect) {
		this.resolvedPath = (redirect) ? target : resolveForRedirect(target);
		this.forRedirect = redirect;
	}
	
	public String consumePath(){
		return this.resolvedPath;
	}
	
	public boolean isForRedirect(){
		return this.forRedirect;
	}
	
	private String resolveForRedirect(final String target){
		if(target.contains(viewBasePath))
			return target;
		StringBuilder pathBuilder = new StringBuilder(viewBasePath);
		pathBuilder.append((target.charAt(0) == '/') ? target.substring(1, target.length()) : target);
		if(pathBuilder.indexOf(viewFileSufix) < 1)
			pathBuilder.append(viewFileSufix);
		return pathBuilder.toString();
	}
}
