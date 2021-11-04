package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import java.io.Serial;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;

/**
 * Provides unformatted messages using resource bundles.
 *
 * <p>Resource bundles are cached in a final map which will grow until it reaches all available
 * locales.</p>
 */
@ApplicationScoped
public class ResourceBundleMessageProvider implements MessageProvider {

  @Serial
  private static final long serialVersionUID = 5376658046230007599L;

  private final Map<BundleLocaleKey, ResourceBundle> cache = new ConcurrentHashMap<>();

  @Override
  public String getString(String bundleName, Locale locale, String helpKey) {
    BundleLocaleKey cacheKey = new BundleLocaleKey(bundleName, locale);

    return Optional.ofNullable(cache.get(cacheKey))
        .or(() -> createBundle(cacheKey))
        .map(bundle -> bundle.getString(helpKey))
        .orElse("");
  }

  private Optional<ResourceBundle> createBundle(BundleLocaleKey cacheKey) {
    try {
      final ResourceBundle resourceBundle
          = ResourceBundle.getBundle(cacheKey.bundleName(), cacheKey.locale());
      cache.put(cacheKey, resourceBundle);

      return Optional.ofNullable(resourceBundle);
    } catch (MissingResourceException missingResourceException) {
      return Optional.empty();
    }
  }

  private static final class BundleLocaleKey {

    private final String bundleName;
    private final Locale locale;

    private BundleLocaleKey(String bundleName, Locale locale) {
      this.bundleName = bundleName;
      this.locale = locale;
    }

    public String bundleName() {
      return bundleName;
    }

    public Locale locale() {
      return locale;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj == null || obj.getClass() != this.getClass()) {
        return false;
      }
      var that = (BundleLocaleKey) obj;
      return Objects.equals(this.bundleName, that.bundleName) &&
          Objects.equals(this.locale, that.locale);
    }

    @Override
    public int hashCode() {
      return Objects.hash(bundleName, locale);
    }

    @Override
    public String toString() {
      return "BundleLocaleKey[" +
          "bundleName=" + bundleName + ", " +
          "locale=" + locale + ']';
    }

    // .
  }
}
