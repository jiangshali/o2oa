package com.x.base.core.project.script;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.BooleanUtils;

import com.x.base.core.entity.JpaObject;
import com.x.base.core.project.config.Config;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class ScriptFactory {

	public static final ScriptEngine scriptEngine = (new ScriptEngineManager())
			.getEngineByName(Config.SCRIPTING_ENGINE_NAME);

	private static CompiledScript COMPILEDSCRIPT_INITIALSERVICESCRIPTTEXT;
	private static CompiledScript COMPILEDSCRIPT_INITIALSCRIPTTEXT;

	public static final String BINDING_NAME_RESOURCES = "resources";
	public static final String BINDING_NAME_EFFECTIVEPERSON = "effectivePerson";
	public static final String BINDING_NAME_PARAMETERS = "parameters";

	public static CompiledScript initialServiceScriptText() throws Exception {
		if (COMPILEDSCRIPT_INITIALSERVICESCRIPTTEXT == null) {
			synchronized (ScriptFactory.class) {
				if (COMPILEDSCRIPT_INITIALSERVICESCRIPTTEXT == null) {
					String text = Config.initialServiceScriptText();
					COMPILEDSCRIPT_INITIALSERVICESCRIPTTEXT = ((Compilable) scriptEngine).compile(text);
				}
			}
		}
		return COMPILEDSCRIPT_INITIALSERVICESCRIPTTEXT;
	}

	public static CompiledScript compile(String text) throws Exception {
		return ((Compilable) scriptEngine).compile(text);
	}

	public static CompiledScript initialScriptText() throws Exception {
		if (COMPILEDSCRIPT_INITIALSCRIPTTEXT == null) {
			synchronized (ScriptFactory.class) {
				if (COMPILEDSCRIPT_INITIALSCRIPTTEXT == null) {
					String text = Config.initialScriptText();
					COMPILEDSCRIPT_INITIALSCRIPTTEXT = ((Compilable) scriptEngine).compile(text);
				}
			}
		}
		return COMPILEDSCRIPT_INITIALSCRIPTTEXT;
	}

	public static String functionalization(String text) {
		StringBuffer sb = new StringBuffer();
		sb.append("(function(){").append(System.lineSeparator());
		sb.append(Objects.toString(text, "")).append(System.lineSeparator());
		sb.append("})();");
		return sb.toString();
	}

	public static List<String> asStringList(Object o) throws Exception {
		return readAsStringList(o);
	}

	public static String asString(Object o) throws Exception {
		return Objects.toString(o);
	}

	public static Boolean asBoolean(Object o) throws Exception {
		return BooleanUtils.toBooleanObject(Objects.toString(o));
	}

	public static List<String> asDistinguishedName(Object o) throws Exception {
		List<String> list = new ArrayList<>();
		if (null != o) {
			if (o instanceof CharSequence) {
				list.add(Objects.toString(o));
			} else if (o instanceof Iterable) {
				for (Object obj : (Iterable<?>) o) {
					if (null != obj) {
						if (obj instanceof CharSequence) {
							list.add(Objects.toString(obj));
						} else {
							Object d = PropertyUtils.getProperty(obj, JpaObject.DISTINGUISHEDNAME);
							if (null != d) {
								list.add(Objects.toString(d));
							}
						}
					}
				}
			} else if (o instanceof ScriptObjectMirror) {
				ScriptObjectMirror som = (ScriptObjectMirror) o;
				if (som.isArray()) {
					Object[] objs = (som.to(Object[].class));
					for (Object obj : objs) {
						if (null != obj) {
							if (obj instanceof CharSequence) {
								list.add(Objects.toString(obj));
							} else {
								Object d = PropertyUtils.getProperty(obj, JpaObject.DISTINGUISHEDNAME);
								if (null != d) {
									list.add(Objects.toString(d));
								}
							}
						}
					}
				} else {
					Object d = PropertyUtils.getProperty(o, JpaObject.DISTINGUISHEDNAME);
					if (null != d) {
						list.add(Objects.toString(d));
					}
				}
			}
		}
		return list;
	}

	private static List<String> readAsStringList(Object obj) throws Exception {
		List<String> list = new ArrayList<>();
		for (Object o : iterator(obj)) {
			list.add(Objects.toString(o));
		}
		return list;
	}

	private static List<Object> iterator(Object obj) throws Exception {
		List<Object> results = new ArrayList<>();
		iterator(obj, results);
		return results;
	}

	private static void iterator(Object obj, List<Object> results) throws Exception {
		if (null == obj) {
			return;
		}
		List<Object> list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			for (Object o : (Object[]) obj) {
				list.add(o);
			}
		} else if (obj instanceof Collection) {
			for (Object o : (Collection<?>) obj) {
				list.add(o);
			}
		} else if (obj instanceof ScriptObjectMirror) {
			ScriptObjectMirror som = (ScriptObjectMirror) obj;
			if (som.isArray()) {
				Object[] os = (som.to(Object[].class));
				for (Object o : os) {
					list.add(o);
				}
			} else {
				results.add(som);
			}
		} else {
			results.add(obj);
		}
		for (Object o : list) {
			iterator(o, results);
		}
	}
}
