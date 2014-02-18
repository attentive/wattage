# Wattage

Wattage is an ID3 tag viewer based on David Nolen's [Om](http://github.com/swannodette/om), which is itself based on Facebook React. If you're not interested in editing music file metadata, this project might still be worth skimming as an example of an Om single-page application with views, view data, widgets and main configuration in separate Clojurescript namespaces, and data retrieved from the server in the EDN exchange format.

At this very early stage the target file (Beethoven's Für Elise) is hard-coded server side. The ultimate plan for this library is to become a *guided* metadata editor, walking you through your MP3/FLAC library prompting you to fill in missing values and correct any suspect ones.

Wattage currently depends on the [wat](http://github.com/attentive/wat) project, a tiny wrapper to JAudioTagger that probably shouldn't be a separate library.


