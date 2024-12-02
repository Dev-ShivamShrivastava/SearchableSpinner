Searchable Spinner

[![](https://jitpack.io/v/Dev-ShivamShrivastava/SearchableSpinner.svg)](https://jitpack.io/#Dev-ShivamShrivastava/SearchableSpinner)

Step:1  Add the jitpack repository to your Project setting file


        dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

Step:2 Add Dependencies

    dependencies {
	        implementation 'com.github.Dev-ShivamShrivastava:SearchableSpinner:1.0.2'
	}

How to Use :- 
  
       textView?.setOnClickListener {

            it.showSearchablePopupAbove(alphabetWords) {
                textView?.text = it
            }
        }
