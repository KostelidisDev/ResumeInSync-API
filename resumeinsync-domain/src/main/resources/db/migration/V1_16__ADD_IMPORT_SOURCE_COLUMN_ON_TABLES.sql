ALTER TABLE volunteer_experiences
ADD import_source TEXT DEFAULT 'ResumeInSync' NOT NULL;

ALTER TABLE professional_experiences
ADD import_source TEXT DEFAULT 'ResumeInSync' NOT NULL;

ALTER TABLE educations
ADD import_source TEXT DEFAULT 'ResumeInSync' NOT NULL;

ALTER TABLE certifications
ADD import_source TEXT DEFAULT 'ResumeInSync' NOT NULL;

ALTER TABLE skills
ADD import_source TEXT DEFAULT 'ResumeInSync' NOT NULL;

ALTER TABLE languages
ADD import_source TEXT DEFAULT 'ResumeInSync' NOT NULL;

ALTER TABLE publications
ADD import_source TEXT DEFAULT 'ResumeInSync' NOT NULL;

ALTER TABLE profiles
ADD import_source TEXT DEFAULT 'ResumeInSync' NOT NULL;