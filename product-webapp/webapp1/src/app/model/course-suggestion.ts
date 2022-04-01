export class CourseSuggestion {
    suggestionId:number;
    category:string;
    skillType:string;
    sourceUrl:string;

    constructor(suggestionId:number,category:string, skillType:string, sourceUrl:string){
        this.suggestionId = suggestionId;
        this.category = category;
        this.skillType = skillType;
        this.sourceUrl = sourceUrl;
    }
}
