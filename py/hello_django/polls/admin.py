'''
Created on Dec 23, 2012

@author: qiuq-gdev
'''

from polls.models import Poll, Choice
from django.contrib import admin

class ChoiceInline(admin.TabularInline):
    model = Choice
    extra = 1

class PollAdmin(admin.ModelAdmin):
    fieldsets = [
        ("Question", {"fields":["question"]}),
        ("Publish Date", {"fields":["pub_date"], "classes":["collapse"]})
    ]
    inlines = [ChoiceInline]
    list_display = ("question", "pub_date", "was_published_recently")
    list_filter = ["pub_date"]
    search_fields = ["question"]
    date_hierarchy = "pub_date"

admin.site.register(Poll, PollAdmin)

admin.site.register(Choice)
